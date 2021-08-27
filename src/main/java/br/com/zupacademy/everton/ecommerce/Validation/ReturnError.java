package br.com.zupacademy.everton.ecommerce.Validation;

import java.util.ArrayList;
import java.util.List;

public class ReturnError {
    private List<String> global = new ArrayList<>();
    private List<FieldErro> fields = new ArrayList<>();

    public void AddError (String errorMessage){
        global.add(errorMessage);
    }

    public void addErrorField (String campo, Object valor, String mensagem){
        FieldErro err = new FieldErro(campo,valor,mensagem);
        fields.add(err);
    }

    public List<?> getGlobal() {
        return global;
    }

    public List<?> getFields() {
        return fields;
    }
}
