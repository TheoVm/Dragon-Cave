import java.io.Serializable;
import java.util.List;

public class MapaConfigurado implements Serializable{
    private final String[][] mapa;
    private final List<Tesouro> tesouros;
    private final List<Perigo> perigos;
    private final Loja loja;
    private final int[] fim;
    private List<Inimigo> inimigosPossiveis;
    private final List<int[]> posicoesInimigos;

    // Construtor que inicializa a classe com os valores recebidos
    public MapaConfigurado(String[][] mapa, List<Tesouro> tesouros, List<Perigo> perigos, Loja loja, int[] fim, List<Inimigo> inimigosPossiveis, List<int[]> posicoesInimigos) {
        this.mapa = mapa;
        this.tesouros = tesouros;
        this.perigos = perigos;
        this.loja = loja;
        this.fim = fim;
        this.inimigosPossiveis = inimigosPossiveis;
        this.posicoesInimigos = posicoesInimigos;
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

    public Loja getLoja(){
        return loja;
    }

    public int[] getFim(){
        return fim;
    }

    public List<int[]> getPosicoes(){
        return posicoesInimigos;
    }

    public List<Inimigo> getInimigosPossiveis(){
        return inimigosPossiveis;
    }
}
