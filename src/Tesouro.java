public abstract class Tesouro extends Itens{
    private final int[] localizacao;
    private final String buff;
     public Tesouro(String nome, int[] localizacao, int valor, int dinheiro) {
        super(nome, valor, dinheiro);
        this.localizacao = localizacao;
        this.buff = "";
    }
    
    public int[] getLocalizacao() {
        return localizacao;
    }

    public abstract String getBuff();
    
    public abstract void efeito(Aventureiro jogador);
    public abstract void removerEfeito(Aventureiro jogador);
}