package br.com.hdlhospitalar.planoDeTestes.dao;

import br.com.hdlhospitalar.planoDeTestes.model.modeloDeTeste;
import br.com.hdlhospitalar.planoDeTestes.model.modeloProcesso;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;


import java.math.BigDecimal;
import java.util.Date;

public class testeDao {
    public static void gerarEvidenciaDoTeste(modeloProcesso processo, modeloDeTeste resultadoTeste, Date inicioTeste){
        JapeSession.SessionHandle hnd = null;
        Date data = new Date();
        Object dataAtual = new java.sql.Timestamp(data.getTime());

        BigDecimal tempoGastoEmSegundos = new BigDecimal((inicioTeste.getTime()-data.getTime())/1000);
        hnd = JapeSession.open();

        try {

            JapeWrapper testeDao = JapeFactory.dao("AD_RESTESTES");
            testeDao.create()
                    .set("CODDEP", processo.getCodDepartamento())
                    .set("IDPROCESSO", processo.getIdProcesso())
                    .set("VERSAO", processo.getVersao())
                    .set("SEQUENCIA", processo.getSequencia())
                    .set("IDTESTE", processo.getIdTeste())
                    .set("DHTESTE", dataAtual)
                    .set("APROVADO",(String) (processo.getResultadoEsperado().equals(resultadoTeste.getResultado()) ? "S" : "N"))
                    .set("TEMPOGASTO", tempoGastoEmSegundos)
                    .set("RESULTADO", resultadoTeste.getResultado())
                    .set("TIPOTESTE", "A")
                    .set("LOG", resultadoTeste.getLogGerado())
                    .set("CODUSUTESTE", BigDecimal.valueOf(0))
                    .save();
        }catch (Exception erro) {
            System.out.println("Ocorreu um erro durante o teste");
            erro.printStackTrace();
        }
    }

}
