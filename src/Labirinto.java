import java.util.*;
import java.io.*;

public class Labirinto implements Serializable{
    private int dificuldade;
    private final String[][] mapa;
    private final List<Tesouro> tesouros;
    private final List<Perigo> perigos;
    private final Loja loja;
    private MapaConfigurado config;
    private final Aventureiro jogador;
    private boolean salaSecretaDesbloqueada;
    private List<Inimigo> inimigos;
    private List<Inimigo> inimigosGerados;
    private List<int[]> posicoesInimigos;
    private int[] fim;
    private Boolean trocar;
    private int tipo;

    public Labirinto(Aventureiro jogador, int dificuldade, int tipo) {
        this.jogador = jogador;
        this.dificuldade = dificuldade;
        this.trocar = false;
        this.tipo = tipo;
        while(true){
            switch (tipo) {
                case 1:
                    this.config = GeradorMapaManual.criarMapaPadrao(dificuldade);
                    break;
                case 2:
                    this.config = GeradorMapaManual2.criarMapaPadrao(dificuldade);
                    break;
                default:
                    break;
            }
            break;
        }
        this.mapa = config.getMapa();
        this.tesouros = config.getTesouros();
        this.perigos = config.getPerigos();
        this.loja = config.getLoja();
        this.fim = config.getFim();
        this.salaSecretaDesbloqueada = false;
        this.inimigos = new ArrayList<>(Arrays.asList(
            new Inimigo("Dragão Lacaio", 25, 10, 10),
            new Inimigo("Dragão Inferior", 50, 15, 20), 
            new Inimigo("Dragão Superior", 80, 25, 30), 
            new Inimigo("Dragão Ancião", 100, 40, 35)
        ));
        this.inimigosGerados = new ArrayList<>();
        this.posicoesInimigos = config.getPosicoes();
        gerarInimigos();
    }

    public void gerarInimigos(){
        Random random = new Random();
        for(int j = 0; j < posicoesInimigos.size(); j++){
            int i = random.nextInt(4);
            Inimigo aux = inimigos.get(i);
            inimigosGerados.add(new Inimigo(aux.getNome(), aux.getVida(), aux.getAtaque(), aux.getDefesa()));
        }
    }

    // public List<int[]> posicoesInimigos(){
    //     List<int[]> posicoes = new ArrayList<>(Arrays.asList(
    //     new int[]{9, 44},
    //     new int[]{9, 45},
    //     new int[]{9, 46},
    //     new int[]{9, 18},
    //     new int[]{8, 18},
    //     new int[]{3, 53},
    //     new int[]{14, 20},
    //     new int[]{15, 20},
    //     new int[]{20, 3},
    //     new int[]{20, 4},
    //     new int[]{20, 5},
    //     new int[]{18, 56},
    //     new int[]{18, 57}, 
    //     new int[]{18, 58},
    //     new int[]{18, 25},
    //     new int[]{18, 26},
    //     new int[]{18, 27}
    //     ));

    //     return posicoes;
    // }

    public void atualizarMapa() {
        // Limpar a posição anterior do jogador no mapa
        for (String[] linha : mapa) {
            for (int j = 0; j < linha.length; j++) {
                if (linha[j].equals("J") || linha[j].equals("I")) {
                    linha[j] = ".";
                }
            }
        }
        
        mapa[loja.getLocalizacao()[0]][loja.getLocalizacao()[1]] = "M";
        mapa[fim[0]][fim[1]] = "F";

        // Adiciona o "J" na posição correta do jogador
        int[] posJogador = jogador.getLocalizacao();
        mapa[posJogador[0]][posJogador[1]] = "J";  // Coloca o "J" na posição atual

        // Atualizar os tesouros e perigos
        for (Perigo p : perigos) {
            int[] posPerigo = p.getLocalizacao();
            mapa[posPerigo[0]][posPerigo[1]] = "P"; // Marca o perigo
        }

        for (Tesouro t : tesouros) {
            int[] posTesouro = t.getLocalizacao();
            mapa[posTesouro[0]][posTesouro[1]] = "T"; // Marca o tesouro
        }

        for (int i = 0; i < posicoesInimigos.size(); i++) {
            int[] posicao = posicoesInimigos.get(i);
            mapa[posicao[0]][posicao[1]] = "X"; // Marca o tesouro
        }

        if (salaSecretaDesbloqueada) mapa[2][4] = "S";
        else mapa[2][4] = "X";
    }

    public void exibirLabirinto() {
        atualizarMapa();

        for (String[] linha : mapa) {
            for (String celula : linha) {
                System.out.print(celula);
            }
            System.out.println(); // Quebra linha após cada linha da matriz
        }

        System.out.println("Vida: " + jogador.getVida() + "/" + jogador.getVidaMAX());
    }

    public boolean verificarFim() {
        int[] loc = jogador.getLocalizacao();
        if (!salaSecretaDesbloqueada && jogador.getTesouros().size() >= 3) {
            salaSecretaDesbloqueada = true;
            System.out.println("Sala secreta desbloqueada em [2,4]!");
        }
        return loc[0] == 4 && loc[1] == 0;
    }

    public int getDificuldade(){
        return dificuldade;
    }

    public List<Tesouro> getTesouros() {
        return tesouros;
    }

    public List<Perigo> getPerigos() {
        return perigos;
    }

    public Loja getLoja() {
        return loja;
    }

    public int getAltura() {
        return mapa.length;
    }

    public int getLargura() {
        return mapa[0].length;
    }

    public String[][] getMapa() {
        return mapa;
    }

    public int[] getFim(){
        return fim;
    }

    public List<Inimigo> getInimigosGerados() {
        return inimigosGerados;
    }

    public List<int[]> getPosicoes() {
        return posicoesInimigos;
    }

    public int getTipo(){
        return tipo;
    }

    public boolean getTrocar(){
        return trocar;
    }

    public void setTrocar(boolean trocar){
        this.trocar = trocar;
    }

    public void verificarSalaSecreta() {
        int[] pos = jogador.getLocalizacao();
        if (salaSecretaDesbloqueada && pos[0] == 2 && pos[1] == 4) {
            System.out.println("Diego é gay"); 
        }
    }


}
