import java.io.Serializable;

public abstract class Itens implements Serializable{
    private final String nome;
    private final int valor;

    public Itens(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome(){
        return nome;
    }
    

    public int getValor() {
        return valor;
    }
    
}
