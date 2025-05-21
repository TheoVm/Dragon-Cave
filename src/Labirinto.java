import java.util.*;

public class Labirinto {
    private String[][] mapa;
    private List<Tesouro> tesouros;
    private List<Perigo> perigos;
    private MapaConfigurado config;
    private Aventureiro jogador;
    private int dificuldade;
    private boolean salaSecretaDesbloqueada;

    public Labirinto(Aventureiro jogador, int dificuldade) {
        this.jogador = jogador;
        this.dificuldade = dificuldade;
        
        // Inicializa o MapaConfigurado e as listas de tesouros e perigos
        this.config = GeradorMapaManual.criarMapaPadrao(dificuldade);
        this.mapa = config.getMapa();
        this.tesouros = config.getTesouros();  // Agora as listas de tesouros e perigos são configuradas aqui
        this.perigos = config.getPerigos();
        this.salaSecretaDesbloqueada = false;

    }


    private void popularMapa() {
        // Usando o mapa, tesouros e perigos do config
        String[][] mapa = this.config.getMapa();

        // Preencher o mapa com tesouros
        for (Tesouro t : tesouros) {
            int[] loc = t.getLocalizacao();
            mapa[loc[0]][loc[1]] = "T"; // Representação do tesouro no mapa
        }

        // Preencher o mapa com perigos
        for (Perigo p : perigos) {
            int[] loc = p.getLocalizacao();
            mapa[loc[0]][loc[1]] = "P"; // Representação do perigo no mapa
        }

        // Colocando o jogador no mapa
        int[] posJogador = jogador.getLocalizacao();
        mapa[posJogador[0]][posJogador[1]] = "J"; // Representação do jogador no mapa
    }

    public void atualizarMapa() {
        // Limpar a posição anterior do jogador no mapa
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j].equals("J")) {
                    mapa[i][j] = ".";  // Remove o "J" de qualquer lugar anterior
                }
            }
        }

        // Agora, adicione novamente o "J" na posição correta do jogador
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

        if (salaSecretaDesbloqueada) mapa[2][4] = "S";
        else mapa[2][4] = "X";
    }

    public void exibirLabirinto() {
        atualizarMapa();

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println(); // Quebra linha após cada linha da matriz
        }

        System.out.println("Vida: " + jogador.getVida());
    }

    public boolean verificarFim() {
        int[] loc = jogador.getLocalizacao();
        if (!salaSecretaDesbloqueada && jogador.getTesouros().size() >= 3) {
            salaSecretaDesbloqueada = true;
            System.out.println("Sala secreta desbloqueada em [2,4]!");
        }
        return loc[0] == 4 && loc[1] == 0; // Verifica se o jogador chegou ao final
    }

    public List<Tesouro> getTesouros() {
        return tesouros;
    }

    public List<Perigo> getPerigos() {
        return perigos;
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

    public void verificarSalaSecreta() {
        int[] pos = jogador.getLocalizacao();
        if (salaSecretaDesbloqueada && pos[0] == 2 && pos[1] == 4) {
            System.out.println("Diego é gay"); 
        }
    }
}
