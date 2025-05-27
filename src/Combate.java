import java.util.Scanner;

public class Combate {
    public static boolean iniciarCombate(Aventureiro jogador, Inimigo inimigo, Scanner scanner) {
        int vidaInicial = inimigo.getVida();
        boolean turnoJogador = true;
        boolean defendendo = false;

        System.out.println("Um inimigo APARECEU! Combate iniciado...");
        
        scanner.nextLine();
        while (jogador.getVida() > 0 && inimigo.getVida() > 0) {
            if (turnoJogador) {
                System.out.println("\nUm " + inimigo.getNome() + " apareceu! Ele está furioso.");
                System.out.println("\nVida do inimigo: " + inimigo.getVida() + "/" + vidaInicial);
                System.out.println("\nSeu turno! Escolha uma ação:");
                System.out.println("1 - Atacar");
                System.out.println("2 - Defender");
                System.out.println("3 - Abraçar");
                System.out.println("4 - Fugir");
                System.out.print("Escolha: ");
                String acao = scanner.nextLine();
                switch (acao) {
                    case "1" -> {
                        int dano = Math.max(0, calcDano(jogador, inimigo, turnoJogador));
                        inimigo.setVida(jogador.getVida() - dano);
                        System.out.println("Você golpeou o inimigo e causou " + dano + " de dano!");
                    }
                    case "2" -> {
                        System.out.println("Você se defendeu!");
                        defendendo = true;
                    }
                    case "3" -> {
                        if (Math.random() < 0.25) {
                            System.out.println("Você deu um abraço! Ele ficou tão confuso que desmaiou");
                            inimigo.setVida(0);
                            return true;
                        } else {
                            System.out.println("O inimigo ve voce se aproximando de braço abertos e golpea voce.");
                        }
                    }
                    case "4" -> {
                        if (Math.random() < 0.25) {
                            System.out.println("Você fugiu do combate!");
                            return false;
                        } else {
                            System.out.println("Você não conseguiu fugir!");
                        }
                    }
                    default -> System.out.println("Ação inválida! Tente novamente.");
                }
            } else {
                int dano = Math.max(0, calcDano(jogador, inimigo, turnoJogador));
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

    public static int calcDano(Aventureiro jogador, Inimigo inimigo, boolean turnoJogador){
        if(!turnoJogador){
            double defesaDecimal = jogador.getDefesa() / 100.0;
            double danoFinal = inimigo.getAtaque() * (1 - defesaDecimal);
            
            if (danoFinal < 0) {
                danoFinal = 0;
            }
            
            double velocidadeDecimal = jogador.getVelocidade() / 100.0;
            if (Math.random() < velocidadeDecimal){
                danoFinal = 0;
            }

            int dano = (int)danoFinal;
            System.out.println(dano);
            return dano;
        } else {
            double defesaDecimal = inimigo.getDefesa() / 100.0;
            double danoFinal = jogador.getAtaque() * (1 - defesaDecimal);
            
            if (danoFinal < 0) {
                danoFinal = 0;
            }
            
            int dano = (int) danoFinal;
            System.out.println(dano);
            return dano;
        }
    }
}