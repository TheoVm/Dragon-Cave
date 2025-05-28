import java.util.*;

public class Boss2 {
    public static MapaConfigurado criarMapaPadrao(int dificuldade) {
        int altura = 30;
        int largura = 100;
        String[][] mapa = new String[altura][largura];

        // ANSI escape code para vermelho
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";

        // Linha superior
        mapa[0][0] = "┌";
        for (int j = 1; j < largura - 1; j++) {
            mapa[0][j] = "─";
        }
        mapa[0][largura - 1] = "┐";

        // Linha inferior
        mapa[altura - 1][0] = "└";
        for (int j = 1; j < largura - 1; j++) {
            mapa[altura - 1][j] = "─";
        }
        mapa[altura - 1][largura - 1] = "┘";

        // Linhas do meio
        for (int i = 1; i < altura - 1; i++) {
            mapa[i][0] = "│";
            for (int j = 1; j < largura - 1; j++) {
                if (j <= 19) {
                    mapa[i][j] = "."; // movimentação
                } else {
                    mapa[i][j] = " "; // espaço para dragão
                }
            }
            mapa[i][largura - 1] = "│";
        }

        // Dragão em ASCII vermelho
        String[] dragao = new String[]{
            "                                                  /===-                        ",
            "                                                 /===-_---~~~~~~~~~------____  ",
            "                                                |===-~___                _,-' `",
            "                 -==\\\\                          `//~\\\\   ~~~~`---.___.-~~      ",
            "             ______-==|                         | |  \\\\           _-~`         ",
            "       __--~~~  ,-/-==\\\\                        | |   `\\        ,'              ",
            "    _-~       /'    |  \\\\                      / /      \\      /                ",
            "  .'        /       |   \\\\                   /' /        \\   /'                ",
            " /  ____  /         |    \\`.__/-~~ ~ \\  __/'  /          \\/'                   ",
            "/-'~    ~~~~~---__  |     ~-/~         ( )   /'        _--~`                    ",
            "                  \\_|      /        _) | ;  ),   __--~~                        ",
            "                    '~~--_/      _-~/- |/ \\   '-~ \\                             ",
            "                   {\\__--_/}    / \\\\_>-|)<__\\      \\                            ",
            "                   /'   (_/  _-~  | |__>--<__|      |                           ",
            "                  |o  o_/) )-~    | |__>--<__|      |                           ",
            "                  / /~ ,_/       / /__>---<__/      |                           ",
            "                 o-o _//        /-~_>---<__-~      /                            ",
            "                 (^(~          /~_>---<__-      _-~                             ",
            "                              /__>--<__/     _-~                                ",
            "                             |__>--<__|     /                  .----_          ",
            "                             |__>--<__|    |                 /' _---_~\\        ",
            "                             |__>--<__|    |               /'  /     ~\\`\\      ",
            "                              \\__>--<__\\    \\            /'  //        ||      ",
            "                               ~-__>--<_~-_  ~--____---~' _/'/        /'       ",
            "                                 ~-_~>--<_/-__       __-~ _/                   ",
            "                                    ~~-'_/_/ /~~~~~~~__--~                     ",
            "                                            ~~~~~~~~~~                          "
        };

        int offsetCol = 20;
        for (int i = 0; i < dragao.length && i + 1 < altura - 1; i++) {
            for (int j = 0; j < dragao[i].length() && j + offsetCol < largura - 1; j++) {
                char c = dragao[i].charAt(j);
                if (c != ' ') {
                    mapa[i + 1][j + offsetCol] = RED + c + RESET;
                }
            }
        }

        // Tesouros e perigos
        List<Tesouro> tesouros = new ArrayList<>();
        List<Perigo> perigos = new ArrayList<>();

        Loja loja = new Loja(new int[]{1, 3});

        tesouros.add(new TesouroArmadura("Armadura leve", new int[]{7, 10}, 15));
        tesouros.add(new TesouroArma("Espada", new int[]{2, 15}, 20));

        for (Tesouro t : tesouros) {
            int[] loc = t.getLocalizacao();
            mapa[loc[0]][loc[1]] = "T";
        }

        for (Perigo p : perigos) {
            int[] loc = p.getLocalizacao();
            mapa[loc[0]][loc[1]] = "P";
        }

        return new MapaConfigurado(mapa, tesouros, perigos, loja);
    }
}
