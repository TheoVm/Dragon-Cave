import java.io.Serializable;

public abstract class Itens implements Serializable{
    private final String nome;
    private final int valor;
    private final int dinheiro;

    public Itens(String nome, int valor, int dinheiro) {
        this.nome = nome;
        this.valor = valor;
        this.dinheiro = dinheiro;
    }

    public String getNome(){
        return nome;
    }
    
    public int getValor() {
        return valor;
    }
    
    public int getDinheiro() {
        return dinheiro;
    }
}
