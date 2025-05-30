import java.io.Serializable;

public class Diario implements Serializable{
    private String nome;
    private String data;
    private String conteudo;
    private int[] localizacao;

    public Diario(String nome, String data, String conteudo, int[] localizacao) {
        this.nome = nome;
        this.data = data;
        this.conteudo = conteudo;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int[] getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(int[] localizacao) {
        this.localizacao = localizacao;
    }
    
}
