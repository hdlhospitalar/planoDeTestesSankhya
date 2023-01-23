package br.com.hdlhospitalar.planoDeTestes.model;

import java.math.BigDecimal;

public class modeloProcesso {
    private BigDecimal codDepartamento;
    private BigDecimal idProcesso;
    private BigDecimal versao;
    private BigDecimal sequencia;
    private BigDecimal idTeste;
    private String resultadoEsperado;
    private String  log;
    private String restultado;
    private BigDecimal tempoGasto;


    public modeloProcesso(BigDecimal codDepartamento, BigDecimal idProcesso, BigDecimal versao, BigDecimal sequencia, BigDecimal idTeste, String resultadoEsperado) {
        this.codDepartamento = codDepartamento;
        this.idProcesso = idProcesso;
        this.versao = versao;
        this.sequencia = sequencia;
        this.idTeste = idTeste;
        this.resultadoEsperado = resultadoEsperado;
    }


    public BigDecimal getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(BigDecimal codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public BigDecimal getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(BigDecimal idProcesso) {
        this.idProcesso = idProcesso;
    }

    public BigDecimal getVersao() {
        return versao;
    }

    public void setVersao(BigDecimal versao) {
        this.versao = versao;
    }

    public BigDecimal getSequencia() {
        return sequencia;
    }

    public void setSequencia(BigDecimal sequencia) {
        this.sequencia = sequencia;
    }

    public BigDecimal getIdTeste() {
        return idTeste;
    }

    public void setIdTeste(BigDecimal idTeste) {
        this.idTeste = idTeste;
    }

    public String getResultadoEsperado() {
        return resultadoEsperado;
    }

    public void setResultadoEsperado(String resultadoEsperado) {
        this.resultadoEsperado = resultadoEsperado;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getRestultado() {
        return restultado;
    }

    public void setRestultado(String restultado) {
        this.restultado = restultado;
    }

    public BigDecimal getTempoGasto() {
        return tempoGasto;
    }

    public void setTempoGasto(BigDecimal tempoGasto) {
        this.tempoGasto = tempoGasto;
    }



}
