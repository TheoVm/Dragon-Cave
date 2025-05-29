public class Chave extends Itens {
    private int quantidade;
    Chave(String nome, int valor, int quantidade){
        super(nome, valor);
        this.quantidade = quantidade;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
}
