import java.util.*;

public class Labirinto {
    private final String[][] mapa;
    private final List<Tesouro> tesouros;
    private final List<Perigo> perigos;
    private final MapaConfigurado config;
    private final Aventureiro jogador;
    private boolean salaSecretaDesbloqueada;
    private final List<Inimigo> inimigos;

    public Labirinto(Aventureiro jogador, int dificuldade) {
        this.jogador = jogador;
        
        // Inicializa o MapaConfigurado e as listas de tesouros e perigos
        this.config = GeradorMapaManual.criarMapaPadrao(dificuldade);
        this.mapa = config.getMapa();
        this.tesouros = config.getTesouros();  // Agora as listas de tesouros e perigos são configuradas aqui
        this.perigos = config.getPerigos();
        this.salaSecretaDesbloqueada = false;
        this.inimigos = config.getInimigos();

    }

    public void atualizarMapa() {
    // Limpar a posição anterior do jogador no mapa
    for (String[] linha : mapa) {
        for (int j = 0; j < linha.length; j++) {
            if (linha[j].equals("J") || linha[j].equals("I")) {
                linha[j] = ".";  // Remove o "J" e "I" de qualquer lugar anterior
            }
        }
    }

    // Adicionar inimigos no mapa
    for (Inimigo inimigo : inimigos) {
        int[] posInimigo = inimigo.getLocalizacao();
        mapa[posInimigo[0]][posInimigo[1]] = "I"; // Marca o inimigo
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

        for (String[] linha : mapa) {
            for (String celula : linha) {
                System.out.print(celula);
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
    public List<Inimigo> getInimigos() {
        return inimigos;
    }
}
