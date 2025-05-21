public abstract class Tesouro {
    private String nome;
    private int[] localizacao;
    private int valor;

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