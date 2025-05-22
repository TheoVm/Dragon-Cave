import java.io.Serializable;

public abstract class Tesouro implements Serializable{
    private final String nome;
    private final int[] localizacao;
    private final int valor;

    public Tesouro(String nome, int[] localizacao, int valor) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int[] getLocalizacao() {
        return localizacao;
    }

    public int getValor() {
        return valor;
    }

    public abstract void efeito(Aventureiro jogador);
}