import java.io.Serializable;

public abstract class Itens implements Serializable{
    private final int valor;

    public Itens(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
}
