import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class JogoLabirinto {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {        
            MusicaUtils.tocarMusica("assets/sounds/awesomeness.wav");

            printComDelay("┌─────────────────────────────────────────────────────────────────────────────────────────────┐",1);
            printComDelay("│                                                                                             │",1);
            printComDelay("│  ██████╗ ██████╗  █████╗  ██████╗  ██████╗ ███╗   ██╗     ██████╗ █████╗ ██╗   ██╗███████╗  │",1);
            printComDelay("│  ██╔══██╗██╔══██╗██╔══██╗██╔════╝ ██╔═══██╗████╗  ██║    ██╔════╝██╔══██╗██║   ██║██╔════╝  │",1);
            printComDelay("│  ██║  ██║██████╔╝███████║██║  ███╗██║   ██║██╔██╗ ██║    ██║     ███████║██║   ██║█████╗    │",1);
            printComDelay("│  ██║  ██║██╔══██╗██╔══██║██║   ██║██║   ██║██║╚██╗██║    ██║     ██╔══██║╚██╗ ██╔╝██╔══╝    │",1);
            printComDelay("│  ██████╔╝██║  ██║██║  ██║╚██████╔╝╚██████╔╝██║ ╚████║    ╚██████╗██║  ██║ ╚████╔╝ ███████╗  │",1);
            printComDelay("│  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝     ╚═════╝╚═╝  ╚═╝  ╚═══╝  ╚══════╝  │",1);
            printComDelay("│                                                                                             │",1);
            printComDelay("│                                 Pressione ENTER para começar                                │",1);
            printComDelay("└─────────────────────────────────────────────────────────────────────────────────────────────┘",1);

            scanner.nextLine(); 
            
            
            boolean jogoIniciado = false;
            int escolha;

            limparTela();
            System.out.println("===== Bem-vindo ao Labirinto! =====\n");
            System.out.println("Símbolos do mapa:");
            System.out.println("J - Você (Aventureiro)");
            System.out.println("P - Princesa (Objetivo final)");
            System.out.println("T - Tesouro (armas ou armaduras para melhorar seu personagem)");
            System.out.println("F - Passagem para o próximo andar");
            System.out.println("M - Loja para compra e venda de itens");
            System.out.println("Use as teclas W (cima), A (esquerda), S (baixo) e D (direita) para se movimentar.\n");
            System.out.println("Boa sorte!");

            
            Aventureiro jogador = null;
            Labirinto labirinto = null;
            int dificuldade = 0;
            
            limparTela();
            while (!jogoIniciado) {
                while (true) {
                    try {
                        System.out.println("Escolha uma das opções:");
                        System.out.println("1 - Iniciar novo jogo");
                        System.out.println("2 - Carregar jogo");
                        escolha = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida! Digite apenas numeros inteiros.");
                        scanner.nextLine(); 
                    }
                }
                while (true) {
                    limparTela();
                    try {
                        switch (escolha) {
                            case 1 -> {
                                printComDelay("Isabella, a princesa do amanhecer foi raptada pelos Dragonites!", 30);                      
                                printComDelay("Resgate-a aventureiro!", 30);
                                System.out.println();
                                int continuar = 0;
                                while (continuar == 0) {
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                    }
                                    limparTela();
                                    try {
                                        printComDelay("Escolha seu personagem:", 20);
                                        printComDelay("1. Tank" , 10);
                                        printComDelay("2. Rogue" , 10);
                                        printComDelay("3. Jorge" , 10);

                                        int escolha2 = scanner.nextInt();
                                        scanner.nextLine();
                                        switch (escolha2) {
                                            case 1 -> {
                                                jogador = new Tank(); 
                                                continuar = 1;
                                            }
                                            case 2 ->{
                                                jogador = new Rogue();
                                                continuar = 1;
                                            }
                                            case 3 -> {
                                                jogador = new Jorge();
                                                continuar = 1;
                                            }
                                            case 666 -> {
                                                jogador = new Sukuna();
                                                continuar = 1;
                                            }
                                            default -> {
                                                System.out.println("Opção inválida.");
                                            }
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Entrada invalida! Digite apenas numeros inteiros.");
                                        scanner.nextLine(); 
                                    }
                                }

                                jogador.addConsumivel("Cura", 20, 2, 20);
                                jogador.addConsumivel("Ataque", 20, 2, 20);
                                jogador.addConsumivel("Defesa", 20, 2, 20);
                                jogador.addConsumivel("Velocidade", 20, 2, 20);

                                while (true) {
                                    limparTela();
                                    try {
                                        System.out.println("Escolha a dificuldade (1 = Fácil, 2 = Médio, 3 = Difícil):");
                                        dificuldade = scanner.nextInt();
                                        scanner.nextLine();
                                        if (dificuldade < 1 || dificuldade > 3) {
                                            System.out.println("Dificuldade inválida! Escolha entre 1, 2 ou 3.");
                                            continue;
                                        }
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Entrada invalida! Digite apenas numeros inteiros.");
                                        scanner.nextLine(); 
                                    }
                                }
                                limparTela();
                                printComDelay("A escuridão aqui é absoluta. Seus olhos, mesmo acostumados às sombras, lutam para enxergar.", 20);                        
                                printComDelay("Mas seu nariz não falha: você sente o cheiro de um ar pesado com o cheiro de mofo apodrecido.", 20);                        
                                printComDelay("Lembre-se aventureiro.", 60);
                                printComDelay("Todo passo, é um passo mais perto.", 60);

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                labirinto = new Labirinto(jogador, dificuldade, 1);
                                jogoIniciado = true;

                            }
                            case 2 -> {
                                ArrayList<Save> saves = Save.carregarListaSaves();

                                if (saves.isEmpty()) {
                                    System.out.println("Nenhum save encontrado.");
                                } else {
                                    System.out.println("Saves disponíveis:");
                                    for (Save s : saves) {
                                        System.out.printf("ID: %d - Nome: %s%n", s.getId(), s.getNome());
                                    }
                                    System.out.println("Digite o ID do save que deseja carregar:");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    Save save = Save.lerSaves(id);
                                    if (save.getId() != -1 && save.getJogador() != null && save.getLabirinto() != null) {
                                        jogador = save.getJogador();
                                        labirinto = save.getLabirinto();
                                        System.out.println("Save carregado com sucesso!");
                                        jogoIniciado = true;
                                    } else {
                                        System.out.println("Save não encontrado ou inválido.");
                                    }
                                }
                            }
                            default -> System.out.println("Opção inválida.");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida! Digite apenas numeros inteiros.");
                        scanner.nextLine(); 
                    }
                }
                
            }

            MusicaUtils.pararMusica();
            
            
            MusicaUtils.tocarMusica("assets/sounds/Exploring-a-Cave.wav");
            while (!labirinto.verificarFim()) {
                if(labirinto.getTrocar()){
                    labirinto = new Labirinto(jogador, dificuldade, labirinto.getTipo() + 1);
                    jogador.setLocalizacao(new int[]{1, 1});
                    jogador.possuiChave(false);

                    if (labirinto.getTipo() == 2) {
                        printComDelay("Com os olhos já acostumados à escuridão, você finalmente enxerga com clareza o chão da caverna", 20);
                        printComDelay("Corpos em estado pútrido de outros aventureiros estão espalhados pelo caminho.", 20);
                        printComDelay("Ainda assim, você continua, pois cada passo é um passo mais perto.", 20);
                    } else if (labirinto.getTipo() == 3) {
                        printComDelay("Exausto após enfrentar inúmeros inimigos, você se apoia nas paredes da imensa caverna para recuperar o fôlego — apenas para perceber a textura viscosa sob seus dedos.", 20);
                        printComDelay("Aquilo já não era mais pedra...", 60);
                        printComDelay("Ainda assim, determinado, você continua, pois cada passo é um passo mais perto.", 20);
                    }
                }
                labirinto.exibirLabirinto();
                System.out.println("Movimente-se (WASD):");
                char comando = scanner.next().toUpperCase().charAt(0);
                switch (comando) {
                    case 'W' -> jogador.mover(-1, 0, labirinto, scanner);
                    case 'A' -> jogador.mover(0, -1, labirinto, scanner);
                    case 'S' -> jogador.mover(1, 0, labirinto, scanner);
                    case 'D' -> jogador.mover(0, 1, labirinto, scanner);
                    case 'E' -> exibirMenu(jogador, labirinto, scanner);
                    default -> System.out.println("Comando inválido.");
                }
            }
            labirinto.verificarSalaSecreta();

            if (jogador.getVida() <= 0) {
                System.out.println("Você morreu!");
                scanner.close();
                return;
            }
        }
    System.out.println("Parabéns! Você escapou do labirinto!");
    }

    public static void printComDelay(String mensagem, int delayMs) {
        for (char c : mensagem.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
    
    public static void exibirMenu(Aventureiro jogador, Labirinto labirinto, Scanner scanner){ 
        int continuar = 1;
        while (continuar == 1) {
            try {
                limparTela();
                System.out.println("\nDificuldade: " + labirinto.getDificuldade());
                System.out.println("===== Menu de Opções =====");
                System.out.println("Escolha uma das opções:");
                System.out.println("1 - Verificar Status");
                System.out.println("2 - Inventário");
                System.out.println("3 - Salvar Jogo");
                System.out.println("4 - Voltar");
                System.out.println("5 - Sair");
                int escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1 -> {
                        jogador.exibirStatus();
                    }
                    case 2 -> {
                        limparTela();
                        System.out.println("===== Inventário =====");
                        System.out.println("Escolha uma das opções:");
                        System.out.println("1 - Verificar Tesouros");
                        System.out.println("2 - Verificar Consumiveis");
                        System.out.println("3 - Verificar Diarios");
                        System.out.println("4 - Voltar");
                        escolha = scanner.nextInt();
                        switch (escolha) {
                            case 1 -> {
                                System.out.println("===== Tesouros =====");
                                jogador.exibirTesouros();
                            }
                            case 2 -> jogador.usarConsumiveis(jogador, scanner);
                            case 3 -> jogador.exibirDiarios(jogador, scanner);
                            default -> System.out.println("Opção inválida.");
                        }                 
                    }
                    case 3 -> {
                        System.out.println("Digite o nome que deseja colocar no seu save:");
                        String nome = scanner.next();
                        Save.abrirArquivo(nome, jogador, labirinto);
                    }
                    case 4 -> {
                        continuar = 0;
                    }
                    case 5 -> {
                        System.exit(0);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida! Digite apenas numeros inteiros.");
                scanner.nextLine(); 
            }
            
        }
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
