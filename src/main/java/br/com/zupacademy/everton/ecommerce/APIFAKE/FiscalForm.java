package br.com.zupacademy.everton.ecommerce.APIFAKE;

public class FiscalForm {

    private Long idUsuario;
    private Long idCompra;

    public FiscalForm(Long idUsuario, Long idCompra) {
        this.idUsuario = idUsuario;
        this.idCompra = idCompra;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    @Override
    public String toString() {
        return "FiscalForm{" +
                "idUsuario=" + idUsuario +
                ", idCompra=" + idCompra +
                '}';
    }
}
