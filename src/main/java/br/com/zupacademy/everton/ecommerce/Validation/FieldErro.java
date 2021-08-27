package br.com.zupacademy.everton.ecommerce.Validation;

public class FieldErro {
    private String campo;
    private Object valor;
    private String mensagem;

    public FieldErro(String campo, Object valor, String mensagem) {
        this.campo = campo;
        this.valor = valor;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public Object getValor() {
        return valor;
    }

    public String getMensagem() {
        return mensagem;
    }
}
