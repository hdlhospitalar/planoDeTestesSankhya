package br.com.hdlhospitalar.planoDeTestes.acaoAgendada;

import br.com.hdlhospitalar.planoDeTestes.logistica.testesLogistica;
import br.com.hdlhospitalar.planoDeTestes.model.modeloDeTeste;
import br.com.hdlhospitalar.planoDeTestes.model.modeloProcesso;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import org.cuckoo.core.ScheduledAction;
import org.cuckoo.core.ScheduledActionContext;

import java.sql.ResultSet;
import java.util.Date;

import static br.com.hdlhospitalar.planoDeTestes.dao.testeDao.gerarEvidenciaDoTeste;

public class executarTestes implements ScheduledAction {
    @Override
    public void onTime(ScheduledActionContext scheduledActionContext) {
        JdbcWrapper jdbc = null;
        EntityFacade dwfEntityFacade = EntityFacadeFactory.getDWFFacade();
        jdbc = dwfEntityFacade.getJdbcWrapper();
        NativeSql nativeSql = null;

        nativeSql = new NativeSql(jdbc);

        StringBuffer sql = new StringBuffer();

        /*Retorna lista de todos os testes*/
        try{
            sql.append("SELECT \n" +
                    "\tCODDEP, IDPROCESSO, VERSAO, SEQUENCIA, IDTESTE, RESULTADOESPERADO\n" +
                    "FROM \n" +
                    "\tAD_PLATESTES PLANO\n" +
                    "WHERE\n" +
                    "\tPLANO.VERSAO = (SELECT MAX(VERSAO) FROM AD_DOCPROCTAR WHERE CODDEP = PLANO.CODDEP AND IDPROCESSO = PLANO.IDPROCESSO)");

            ResultSet rs = null;

            rs = nativeSql.executeQuery(sql);

            /*Testes logistica*/
            while (rs.next()) {
                Date inicioTeste = new Date();

                modeloProcesso processo = new modeloProcesso(rs.getBigDecimal("CODDEP"),rs.getBigDecimal("IDPROCESSO"),
                        rs.getBigDecimal("VERSAO"),rs.getBigDecimal("SEQUENCIA"),rs.getBigDecimal("IDTESTE"),rs.getString("RESULTADOESPERADO"));

                modeloDeTeste resultadoTeste = testesLogistica.validacoesLogistica(processo, nativeSql);

                gerarEvidenciaDoTeste(processo, resultadoTeste, inicioTeste);
            }

        } catch (Exception erro){
            System.out.println("Ocorreu um erro ao tentar atualizar os preços dos produtos ( processo de entrada de mercadorias flow )");
            erro.printStackTrace();
        }
    }

}