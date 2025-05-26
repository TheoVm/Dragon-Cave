public abstract class Tesouro extends Itens{
    private final String nome;
    private final int[] localizacao;
     public Tesouro(String nome, int[] localizacao, int valor) {
        super(valor);
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public int[] getLocalizacao() {
        return localizacao;
    }
    
    public abstract void efeito(Aventureiro jogador);
}