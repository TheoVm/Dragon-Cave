import java.util.List;

public class MapaConfigurado {
    private String[][] mapa;        // Matriz que armazena o mapa
    private List<Tesouro> tesouros; // Lista de tesouros
    private List<Perigo> perigos;   // Lista de perigos
    private int[] posInicial;       // Posição inicial do jogador

    // Construtor que inicializa a classe com os valores recebidos
    public MapaConfigurado(String[][] mapa, List<Tesouro> tesouros, List<Perigo> perigos, int[] posInicial) {
        this.mapa = mapa;
        this.tesouros = tesouros;
        this.perigos = perigos;
        this.posInicial = posInicial;
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

    public int[] getPosInicial() {
        return posInicial;
    }
}
