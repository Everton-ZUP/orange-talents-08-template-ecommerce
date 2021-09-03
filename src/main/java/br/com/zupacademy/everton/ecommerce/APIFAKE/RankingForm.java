package br.com.zupacademy.everton.ecommerce.APIFAKE;

public class RankingForm {
    private Long idCompra;
    private Long idVendedor;

    public RankingForm(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    @Override
    public String toString() {
        return "RankingForm{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
