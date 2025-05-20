    import java.util.*;

    public class Labirinto {
        private String[][] mapa;
        private List<Tesouro> tesouros;
        private List<Perigo> perigos;
        private Aventureiro jogador;
        private int dificuldade;
        private boolean salaSecretaDesbloqueada;

        public Labirinto(Aventureiro jogador, int dificuldade) {
            this.jogador = jogador;
            this.dificuldade = dificuldade;
            this.mapa = gerarMapa();
            this.tesouros = new ArrayList<>();
            this.perigos = new ArrayList<>();
            this.salaSecretaDesbloqueada = false;
            popularMapa();
        }

        private String[][] gerarMapa() {
            int altura = 10;
            int comprimento = 20;
            String[][] mapa = new String[altura][comprimento];
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < comprimento; j++) {
                    mapa[i][j] = ".";
                }
            }
            return mapa;
        }

        private void popularMapa() {
            jogador.setLocalizacao(new int[]{1, 1});
            mapa[1][1] = "J";

            tesouros.add(new TesouroArma("Adaga", new int[]{2, 2}, 10));
            tesouros.add(new TesouroArmadura("Armadura leve", new int[]{9, 16}, 15));
            tesouros.add(new TesouroArma("Espada", new int[]{3, 1}, 20));
            tesouros.add(new TesouroArmadura("Armadura padrão", new int[]{4, 2}, 20));
            tesouros.add(new TesouroArma("Machado", new int[]{2, 6}, 25));

            perigos.add(new Perigo(new int[]{1, 2}, 10 * dificuldade));
            perigos.add(new Perigo(new int[]{3, 3}, 20 * dificuldade));
            perigos.add(new Perigo(new int[]{4, 4}, 30 * dificuldade));

            mapa[4][0] = "F";
            mapa[2][4] = "X";
        }

        public void atualizarMapa() {

            for (int i = 0; i < mapa.length; i++) {
                for (int j = 0; j < mapa[i].length; j++) {
                    mapa[i][j] = ".";
                }
            }

            for(int i = 3; i < 9; i++){
                mapa[4][i] = "@";
            }

            for (Perigo p : perigos) {
                int[] pos = p.getLocalizacao();
                mapa[pos[0]][pos[1]] = "P";
            }
            for (Tesouro t : tesouros) {
                int[] pos = t.getLocalizacao();
                mapa[pos[0]][pos[1]] = "T";
            }

            for(int i = 0; i < 10; i++){
                mapa[i][0] = "|";
                mapa[i][20-1] = "|";
            }

            for(int j = 0; j < 20; j++){
                mapa[0][j] = "_";
                mapa[10 - 1][j] = "_";
            }

            int[] j = jogador.getLocalizacao();
            mapa[j[0]][j[1]] = "J";
            mapa[4][0] = "F";
            if (salaSecretaDesbloqueada) mapa[2][4] = "S";
            else mapa[2][4] = "X";
        }

        public void exibirLabirinto() {
            atualizarMapa();
            for (String[] linha : mapa) {
                for (String celula : linha) {
                    System.out.print(celula + " ");
                }
                System.out.println();
            }
            System.out.println("Vida: " + jogador.getVida());
        }

        public boolean verificarFim() {
            int[] loc = jogador.getLocalizacao();
            if (!salaSecretaDesbloqueada && jogador.getTesouros().size() >= 3) {
                salaSecretaDesbloqueada = true;
                System.out.println("Sala secreta desbloqueada em [2,4]!");
            }
            return loc[0] == 4 && loc[1] == 0;
        }

        public List<Tesouro> getTesouros(){ 
            return tesouros; 
        }
        public List<Perigo> getPerigos(){ 
            return perigos;
        }

        public int getAltura() {
        return mapa.length;
        }

        public int getLargura() {
        return mapa[0].length;
        }

        public String[][] getMapa(){
            return mapa;
        }

        public void verificarSalaSecreta() {
            int[] pos = jogador.getLocalizacao();
            if (salaSecretaDesbloqueada && pos[0] == 2 && pos[1] == 4) {
                System.out.println("Diego é gay");
            }
        }
    }