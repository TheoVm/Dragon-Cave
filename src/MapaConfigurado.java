import java.io.Serializable;
import java.util.List;

public class MapaConfigurado implements Serializable{
    private final String[][] mapa;        // Matriz que armazena o mapa
    private final List<Tesouro> tesouros; // Lista de tesouros
    private final List<Perigo> perigos;   // Lista de perigos
    private final List<Inimigo> inimigos; // Lista de inimigos


    // Construtor que inicializa a classe com os valores recebidos
    public MapaConfigurado(String[][] mapa, List<Tesouro> tesouros, List<Perigo> perigos, int[] posInicial, List<Inimigo> inimigos) {
        this.mapa = mapa;
        this.tesouros = tesouros;
        this.perigos = perigos;
        this.inimigos = inimigos;
    }

    // Métodos para acessar as informações do mapa
    public String[][] getMapa() {
        return mapa;
    }

    public List<Tesouro> getTesouros() {
        return tesouros;
    }

    public List<Perigo> getPerigos() {
        return perigos;
    }

     public List<Inimigo> getInimigos() {
        return inimigos;
    }
}
