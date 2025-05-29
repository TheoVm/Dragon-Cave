import java.util.*;

public class Boss {
    public static MapaConfigurado criarMapaPadrao(int dificuldade) {
        int altura = 20;
        int largura = 80;
        String[][] mapa = new String[altura][largura];

        // Códigos ANSI para cor vermelha
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";

        // Linha superior (borda)
        mapa[0][0] = "┌";
        for (int j = 1; j < largura - 1; j++) {
            mapa[0][j] = "─";
        }
        mapa[0][largura - 1] = "┐";

        // Linha inferior (borda)
        mapa[altura - 1][0] = "└";
        for (int j = 1; j < largura - 1; j++) {
            mapa[altura - 1][j] = "─";
        }
        mapa[altura - 1][largura - 1] = "┘";

        // Linhas do meio
        for (int i = 1; i < altura - 1; i++) {
            mapa[i][0] = "│";
            for (int j = 1; j < largura - 1; j++) {
                if (j < 20) {
                    mapa[i][j] = "."; // espaço de movimentação
                } else {
                    mapa[i][j] = " "; // espaço vazio (sem ponto)
                }
            }
            mapa[i][largura - 1] = "│";
        }

        // Dragão ASCII
        String[] dragao = new String[]{
            "                 ___====-_  _-====___",
            "           _--^^^#####//      \\\\#####^^^--_",
            "        _-^##########// (    ) \\\\##########^-_",
            "       -############//  |\\^^/|  \\\\############-",
            "     _/############//   (@::@)   \\\\############\\_",
            "    /#############((     \\\\//     ))#############\\",
            "   -###############\\\\    (oo)    //###############-",
            "  -#################\\\\  / VV \\  //#################-",
            " -###################\\\\/      \\\\//###################-",
            "_#/|##########/\\######(   /\\   )######/\\##########|\\#_",
            "|/ |#/\\#/\\#/\\/  \\#/\\##\\  |  |  /##/\\#/  \\/\\#/\\#/\\#| \\|",
            "`  |/  V  V  `   V  \\#\\| |  | |/#/  V   '  V  V  \\|  '",
            "   `   `  `      `   / | |  | | \\   '      '  '   '",
            "                    (  | |  | |  )",
            "                   __\\ | |  | | /__",
            "                  (vvv(VVV)(VVV)vvv)"
        };

        

        // Inserir o dragão a partir da linha 1 e coluna 20 (em vermelho)
        int offsetCol = 20;
        for (int i = 0; i < dragao.length; i++) {
            for (int j = 0; j < dragao[i].length(); j++) {
                int linha = i + 1;
                int coluna = j + offsetCol;
                if (linha < altura - 1 && coluna < largura - 1) {
                    mapa[linha][coluna] = ANSI_RED + dragao[i].charAt(j) + ANSI_RESET;
                }
            }
        }

        // Listas de tesouros e perigos
        List<Tesouro> tesouros = new ArrayList<>();
        List<Perigo> perigos = new ArrayList<>();
        List<int[]> posicoes = new ArrayList<>();

        Loja loja = new Loja(new int[]{1, 3});

        // Tesouros
        tesouros.add(new TesouroArmadura("Armadura leve", new int[]{7, 10}, 15));
        tesouros.add(new TesouroArma("Espada", new int[]{1, 18}, 20));  // dentro da zona de movimentação

        // Inserir tesouros no mapa
        for (Tesouro t : tesouros) {
            int[] loc = t.getLocalizacao();
            mapa[loc[0]][loc[1]] = "T";
        }

        // Inserir perigos no mapa
        for (Perigo p : perigos) {
            int[] loc = p.getLocalizacao();
            mapa[loc[0]][loc[1]] = "P";
        }

        int[] fim = new int[]{};

        return new MapaConfigurado(mapa, tesouros, perigos, loja, fim, posicoes);
    }

    public static String[] append(String[] arr, String element) {
        String[] result = new String[arr.length + 1];
        System.arraycopy(arr, 0, result, 0, arr.length);
        result[arr.length] = element;
        return result;
    }
}
