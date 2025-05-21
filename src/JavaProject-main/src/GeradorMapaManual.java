import java.util.ArrayList;
import java.util.List;

public class GeradorMapaManual {
    public static MapaConfigurado criarMapaPadrao(int dificuldade) {
        int altura = 10;
        int largura = 20;
        String[][] mapa = new String[altura][largura];

        // Preencher o mapa com o chão (.) e bordas externas
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                mapa[i][j] = ".";
            }
        }

        // Adicionar bordas externas
        mapa[0][0] = "╔";
        mapa[0][largura - 1] = "╗";
        mapa[altura - 1][0] = "╚";
        mapa[altura - 1][largura - 1] = "╝";
        
        for (int i = 1; i < largura - 1; i++) {
            mapa[0][i] = "═"; // linha superior
            mapa[altura - 1][i] = "═"; // linha inferior
        }
        
        for (int i = 1; i < altura - 1; i++) {
            mapa[i][0] = "║"; // coluna esquerda
            mapa[i][largura - 1] = "║"; // coluna direita
        }

        // Adicionar paredes internas usando caracteres de borda
        mapa[1][5] = "╠"; mapa[1][6] = "═"; mapa[1][7] = "╦"; mapa[2][6] = "║";
        mapa[2][5] = "╬"; mapa[2][7] = "╣"; mapa[3][5] = "║"; mapa[3][6] = "╠";
        mapa[3][7] = "╝"; mapa[4][6] = "╩"; mapa[5][6] = "═"; mapa[6][7] = "║";
        mapa[6][7] = "╩"; mapa[5][2] = "╠"; mapa[4][2] = "═"; 
        
        // Listas de tesouros e perigos
        List<Tesouro> tesouros = new ArrayList<>();
        List<Perigo> perigos = new ArrayList<>();
        
        // Tesouros
        tesouros.add(new TesouroArma("Espada", new int[]{1, 9}, 20)); // Tesouro em (1,9)
        tesouros.add(new TesouroArmadura("Armadura leve", new int[]{7, 10}, 15)); // Tesouro em (7,10)
        tesouros.add(new TesouroArma("Machado", new int[]{8, 18}, 25)); // Tesouro em (9,18)

        // Perigos
        perigos.add(new Perigo(new int[]{1, 18}, 10 * dificuldade)); // Perigo em (1,19)
        perigos.add(new Perigo(new int[]{8, 3}, 15 * dificuldade)); // Perigo em (9,3)

        // Posição inicial do jogador
        int[] posInicial = new int[]{1, 1};
        mapa[posInicial[0]][posInicial[1]] = "J"; // Jogador em (1,1)

        // Inserir tesouros e perigos no mapa
        for (Tesouro t : tesouros) {
            int[] loc = t.getLocalizacao();
            mapa[loc[0]][loc[1]] = "T"; // Marcador de tesouro
        }
        
        for (Perigo p : perigos) {
            int[] loc = p.getLocalizacao();
            mapa[loc[0]][loc[1]] = "P"; // Marcador de perigo
        }
        List<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo(30, 8, 3, new int[]{3,3}));

        mapa[9][18] = "F"; 
        // Retornar um novo objeto MapaConfigurado
        return new MapaConfigurado(mapa, tesouros, perigos, posInicial, inimigos);
    }
}
