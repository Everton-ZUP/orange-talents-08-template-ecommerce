package br.com.zupacademy.everton.ecommerce.Validation;

public class ErroRegraNegocio extends RuntimeException {

    private ReturnError returnError;

    public ErroRegraNegocio(String mensagem, String campo,Object value) {
        this.returnError = new ReturnError();
        returnError.addErrorField(campo,value,mensagem);
    }

    public ReturnError getReturnError() {
        return returnError;
    }
}
