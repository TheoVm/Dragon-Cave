import java.util.Scanner;

public class JogoLabirinto {

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Bem-vindo ao Labirinto! =====");
        System.out.println("Símbolos do mapa:");
        System.out.println("J - Você (Aventureiro)");
        System.out.println("T - Tesouro (armas ou armaduras para melhorar seu personagem)");
        System.out.println("P - Perigo (cuidado! Reduz sua vida ao entrar)");
        System.out.println("F - Saída do labirinto (objetivo final)");
        System.out.println("X - Sala secreta bloqueada (desbloqueia após coletar 3 tesouros)");
        System.out.println("S - Sala secreta desbloqueada (contém surpresas!)");
        System.out.println("Use as teclas W (cima), A (esquerda), S (baixo) e D (direita) para se movimentar.");
        System.out.println("Boa sorte!");



        System.out.println("Escolha seu personagem:");
        System.out.println("1. Tank");
        System.out.println("2. Rogue");
        System.out.println("3. Jorge");

        Aventureiro jogador = null;
        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                jogador = new Tank();
                break;
            case 2:
                jogador = new Rogue();
                break;
            case 3:
                jogador = new Jorge();
                break;
            default:
                System.out.println("Opção inválida. Jorge selecionado por padrão.");
                jogador = new Jorge();
        }

        System.out.println("Escolha a dificuldade (1 = Fácil, 2 = Médio, 3 = Difícil):");
        int dificuldade = scanner.nextInt();
        Labirinto labirinto = new Labirinto(jogador, dificuldade);

        while (!labirinto.verificarFim()) {
            labirinto.exibirLabirinto();
            System.out.println("Movimente-se (WASD):");
            char comando = scanner.next().toUpperCase().charAt(0);
            switch (comando) {
                case 'W': jogador.mover(-1, 0, labirinto); break;
                case 'A': jogador.mover(0, -1, labirinto); break;
                case 'S': jogador.mover(1, 0, labirinto); break;
                case 'D': jogador.mover(0, 1, labirinto); break;
                default: System.out.println("Comando inválido.");
            }

            labirinto.verificarSalaSecreta();

            if (jogador.getVida() <= 0) {
                System.out.println("Você morreu!");
                scanner.close();  // Fecha scanner antes de sair
                return;
            }
        }
        System.out.println("Parabéns! Você escapou do labirinto!");
        scanner.close();  // Fecha scanner no final normalmente
    }
}
