package br.com.zupacademy.everton.ecommerce.Validation;

public class ErroRegraNegocio extends RuntimeException {

    private ReturnError returnError;

    public ErroRegraNegocio(String mensagem, String campo,Object qtd) {
        this.returnError = new ReturnError();
        returnError.addErrorField(campo,qtd,mensagem);
    }

    public ReturnError getReturnError() {
        return returnError;
    }
}
