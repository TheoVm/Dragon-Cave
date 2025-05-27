public abstract class Tesouro extends Itens{
    private final String nome;
    private final int[] localizacao;
    private final String buff;
     public Tesouro(String nome, int[] localizacao, int valor) {
        super(valor);
        this.nome = nome;
        this.localizacao = localizacao;
        this.buff = "";
    }

    public String getNome() {
        return nome;
    }

    public int[] getLocalizacao() {
        return localizacao;
    }

    public abstract String getBuff();
    
    public abstract void efeito(Aventureiro jogador);
}