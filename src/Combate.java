import java.util.Scanner;

public class Combate {
    public static boolean iniciarCombate(Aventureiro jogador, Inimigo inimigo, Scanner scanner) {
        boolean turnoJogador = true;
        boolean defendendo = false;

        System.out.println("Um inimigo APARECEU! Combate iniciado...");
        
        scanner.nextLine();
        while (jogador.getVida() > 0 && inimigo.getVida() > 0) {
            if (turnoJogador) {
                System.out.println("\nSeu turno! Escolha uma ação:");
                System.out.println("1 - Atacar");
                System.out.println("2 - Defender");
                System.out.println("3 - Abraçar");
                System.out.println("4 - Fugir");
                System.out.print("Escolha: ");
                String acao = scanner.nextLine();
                switch (acao) {
                    case "1" -> {
                        int dano = jogador.atacar(inimigo);
                        System.out.println("Você golpeou o inimigo e causou " + dano + " de dano!");
                    }
                    case "2" -> {
                        System.out.println("Você se defendeu!");
                        defendendo = true;
                    }
                    case "3" -> {
                        if (Math.random() < 0.25) {
                            System.out.println("Você deu um abraço Kawaii >///< no inimigo! Ele ficou tão confuso que desmaiou");
                            inimigo.setVida(0);
                            return true;
                        } else {
                            System.out.println("O inimigo acha que você é biba e não se importa com o abraço.");
                        }
                    }
                    case "4" -> {
                        if (Math.random() < 0.5) {
                            System.out.println("Você fugiu do combate!");
                            return false;
                        } else {
                            System.out.println("Você não conseguiu fugir!");
                        }
                    }
                    default -> System.out.println("Ação inválida! Tente novamente.");
                }
            } else {
                int dano = Math.max(0, inimigo.getAtaque() - jogador.getDefesa());
                if (defendendo) {
                    dano = dano / 2;
                    defendendo = false;
                }
                jogador.setVida(jogador.getVida() - dano);
                System.out.println("O inimigo atacou! Você perdeu " + dano + " de vida. Sua vida: " + jogador.getVida());
            }
            turnoJogador = !turnoJogador;
        }
        if (jogador.getVida() <= 0) {
            return false;
        } else {
            return true;
        }
    }
}