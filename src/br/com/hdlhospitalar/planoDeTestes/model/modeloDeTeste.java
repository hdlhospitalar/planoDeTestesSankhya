package br.com.hdlhospitalar.planoDeTestes.model;

import java.math.BigDecimal;

public class modeloDeTeste {
    private boolean aprovado;

    private BigDecimal tempoGasto;
    private String resultado;
    private String logGerado;

    public modeloDeTeste(boolean aprovado, BigDecimal tempoGasto, String resultado, String logGerado) {
        this.aprovado = aprovado;
        this.tempoGasto = tempoGasto;
        this.resultado = resultado;
        this.logGerado = logGerado;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public BigDecimal getTempoGasto() {
        return tempoGasto;
    }

    public void setTempoGasto(BigDecimal tempoGasto) {
        this.tempoGasto = tempoGasto;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getLogGerado() {
        return logGerado;
    }

    public void setLogGerado(String logGerado) {
        this.logGerado = logGerado;
    }
}
