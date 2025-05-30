import java.util.Scanner;

public class Combate {
    public static boolean iniciarCombate(Aventureiro jogador, Inimigo inimigo, Scanner scanner) {
        int vidaInicial = inimigo.getVida();
        boolean turnoJogador = true;
        boolean defendendo = false;
        int continuar;

        System.out.println("Inimigo encontrado! Combate iniciado...");
        System.out.println("\nUm " + inimigo.getNome() + " apareceu!");
        
        scanner.nextLine();
        while (jogador.getVida() > 0 && inimigo.getVida() > 0) {
            if (turnoJogador) {
                continuar = 0;
                while (continuar == 0) {
                    System.out.println("\nSua vida: " + jogador.getVida() + "/" + jogador.getVidaMAX());
                    System.out.println("\nVida do inimigo: " + inimigo.getVida() + "/" + vidaInicial);
                    System.out.println("\nSeu turno! Escolha uma ação:");
                    System.out.println("1 - Atacar");
                    System.out.println("2 - Defender");
                    System.out.println("3 - Abraçar");
                    System.out.println("4 - Fugir");
                    System.out.print("Escolha: ");
                    String acao = scanner.nextLine();
                    System.out.println("");
                    switch (acao) {
                        case "1" -> {
                            int dano = Math.max(0, calcDano(jogador, inimigo, turnoJogador));
                            inimigo.setVida(inimigo.getVida() - dano);
                            System.out.println("Você golpeou o inimigo e causou " + dano + " de dano!");
                            continuar = 1; 
                        }
                        case "2" -> {
                            System.out.println("Você se defendeu!");
                            defendendo = true;
                            continuar = 1;
                        }
                        case "3" -> {
                            if (Math.random() < 0.05) {
                                System.out.println("Você deu um abraço! Ele ficou tão confuso que ficou vulneravel pelo resto da batalha!");
                                inimigo.setDefesa(0);
                            } else {
                                System.out.println("O inimigo vê você se aproximando de braço abertos e ataca você.");
                            }
                            continuar = 1; 
                        }
                        case "4" -> {
                            if (Math.random() < 0.20) {
                                System.out.println("Você fugiu do combate!");
                                return false;
                            } else {
                                System.out.println("Você não conseguiu fugir!");
                            }
                            continuar = 1; 
                        }
                        default -> System.out.println("Ação inválida! Tente novamente.");
                    }
                }
            } else {
                int dano = calcDano(jogador, inimigo, turnoJogador);
                if (defendendo && dano >= 0) {
                    dano = dano / 2;
                    defendendo = false;
                }
                if (dano == -1){
                    System.out.println("O inimigo atacou! Mas usando sua velocidade, você esquiva do ataque!");
                } else {
                    jogador.setVida(jogador.getVida() - dano);
                    System.out.println("O inimigo atacou! Você perdeu " + dano + " de vida.");
                }
            }
            turnoJogador = !turnoJogador;
            if(inimigo.getVida() <= 0){
                System.out.println("Você adiquiriu " + inimigo.getValor() + " de ouro!");
                jogador.setOuro(jogador.getOuro() + inimigo.getValor());
            }
        }
        if (jogador.getVida() <= 0) {
            return false;
        } else {
            for(int i = 0; i < jogador.getEfeitos().size(); i ++){
                String[] efeito = jogador.getEfeitos().get(i);
                if (Integer.parseInt(efeito[1]) > 0){
                    String valor = efeito[1];
                    String novoValor = String.valueOf(Integer.parseInt(valor) - 1);
                    jogador.setEfeito(novoValor, i);
                    
                    if (Integer.parseInt(efeito[1]) == 0){
                        String tipo = efeito[0];
                        switch (tipo) {
                            case "Ataque":
                                jogador.setAtaque(jogador.getAtaque() - 10);
                                System.out.println("Efeito da poção terminado, ataque voltou ao valor original.");
                                break;
                            case "Defesa":
                                jogador.setDefesa((jogador.getDefesa() - 10));
                                System.out.println("Efeito da poção terminado, defesa voltou ao valor original.");
                                break;
                            case "Velocidade":
                                jogador.setVelocidade((jogador.getVelocidade() - 10));
                                System.out.println("Efeito da poção terminado, velocidade voltou ao valor original.");
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
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
                danoFinal = -1;
            }

            int dano = (int)danoFinal;
            return dano;
        } else {
            double defesaDecimal = inimigo.getDefesa() / 100.0;
            double danoFinal = jogador.getAtaque() * (1 - defesaDecimal);
            
            if (danoFinal < 0) {
                danoFinal = -1;
            }
            
            int dano = (int) danoFinal;
            return dano;
        }
    }
}