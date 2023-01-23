package br.com.hdlhospitalar.planoDeTestes.logistica;

import br.com.hdlhospitalar.planoDeTestes.model.modeloDeTeste;
import br.com.hdlhospitalar.planoDeTestes.model.modeloProcesso;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;

public class testesLogistica {
    public static modeloDeTeste validacoesLogistica(modeloProcesso processo, NativeSql nativeSql) throws Exception {

        //processo.getIdProcesso() == BigDecimal.valueOf(1) &&
        //processo.getVersao() == BigDecimal.valueOf(1) &&
        //processo.getSequencia() == BigDecimal.valueOf(1) &&
        //processo.getIdTeste() == BigDecimal.valueOf(1)
        modeloDeTeste resultadoTeste = new modeloDeTeste(false,null,null,null);

        BigDecimal codDepartamento = BigDecimal.valueOf(44010000);

        /*Valida se é o departamento logistica*/
        if(codDepartamento.compareTo(processo.getCodDepartamento()) == 0){

            /* Testa se todos os produtos de revenda estão marcados para controlar local e se estão marcados para controlar lote por numero*/
            if(        processo.getIdProcesso().compareTo(BigDecimal.valueOf(1)) == 0 &&
                       processo.getVersao().compareTo(BigDecimal.valueOf(1)) == 0 &&
                       processo.getSequencia().compareTo(BigDecimal.valueOf(1)) == 0 &&
                       processo.getIdTeste().compareTo(BigDecimal.valueOf(1)) == 0){
                System.out.println("Entrei aqui para testar controle e local");
                resultadoTeste = validaCOnfiguracoesDeLotesELocaisDeProdutosRevenda();

            }

        }

        return resultadoTeste;
    }

    private static modeloDeTeste validaCOnfiguracoesDeLotesELocaisDeProdutosRevenda() throws  Exception
    {
        modeloDeTeste resultadoTeste = new modeloDeTeste(false,null,null,null);
        try {

        JdbcWrapper jdbc = null;
        EntityFacade dwfEntityFacade = EntityFacadeFactory.getDWFFacade();
        jdbc = dwfEntityFacade.getJdbcWrapper();
        NativeSql nativeSql = new NativeSql(jdbc);

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT\n" +
                "\tCASE WHEN textoDeLog IS NULL THEN '1' ELSE '-1' END retorno,\n" +
                "\ttextoDeLog\n" +
                "FROM\n" +
                "(SELECT\n" +
                "    SUBSTRING((SELECT \n" +
                "\t(CONCAT('Produto ', CODPROD, '-', SANKHYA.FUNCTION_RETORNA_PRIMEIRA_LETRA_MAIUSCULA_HDL(TRIM(DESCRPROD)), ' precisa ser configurado para: ', \n" +
                "\tCASE WHEN ISNULL(USALOCAL, 'N') <> 'S' THEN '|| Controlado por: \"Número do lote ||\"' else '' end,\n" +
                "\tCASE WHEN ISNULL(RASTRESTOQUE, '') <> 'C' THEN ' || Usa local: \"Sim\" ||' ELSE '' END)  + CHAR(13)+CHAR(10))\n" +
                "FROM \n" +
                "\tTGFPRO\n" +
                "WHERE \n" +
                "\tUSOPROD = 'R' \n" +
                "AND (ISNULL(USALOCAL, 'N') <> 'S' OR ISNULL(RASTRESTOQUE, '') <> 'C')\n" +
                "FOR XML PATH ('')),1,50000) textoDeLog)TAB");

        ResultSet rs = null;

        rs = nativeSql.executeQuery(sql);

        while (rs.next()) {
            resultadoTeste.setResultado(rs.getString("retorno"));
            resultadoTeste.setLogGerado(rs.getString("textoDeLog"));
        }


    } catch (Exception erro){
            erro.printStackTrace();
        }
        return resultadoTeste;
    };
};
