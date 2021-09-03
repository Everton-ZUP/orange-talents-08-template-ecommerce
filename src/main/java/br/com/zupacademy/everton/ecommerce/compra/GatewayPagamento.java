package br.com.zupacademy.everton.ecommerce.compra;

public enum GatewayPagamento {
    PAGSEGURO{
        @Override
        String urlRetorno(Long id) {
            return "pagseguro.com?returnId={" + id.toString() + "}&redirectUrl=http://localhost:8080/pag-seguro";
        }
    },
    PAYPAL{
        @Override
        String urlRetorno(Long id) {
            return "paypal.com?buyerId={" + id.toString() + "}&redirectUrl=http://localhost:8080/paypal";
        }
    };

    abstract String urlRetorno(Long id);
}
