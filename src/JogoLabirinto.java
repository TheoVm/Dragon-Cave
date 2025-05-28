import java.util.ArrayList;
import java.util.Scanner;


public class JogoLabirinto {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            boolean jogoIniciado = false;

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

            
            Aventureiro jogador = null;
            Labirinto labirinto = null;

            while (!jogoIniciado) {
                System.out.println("Escolha uma das opções:");
                System.out.println("1 - Iniciar novo jogo");
                System.out.println("2 - Carregar jogo");
                int escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1 -> {
                        System.out.println("Escolha seu personagem:");
                        System.out.println("1. Tank");
                        System.out.println("2. Rogue");
                        System.out.println("3. Jorge");

                        int escolha2 = scanner.nextInt();
                        scanner.nextLine();
                        switch (escolha2) {
                            case 1 -> jogador = new Tank();
                            case 2 -> jogador = new Rogue();
                            case 3 -> jogador = new Jorge();
                            default -> {
                                System.out.println("Opção inválida. Jorge selecionado por padrão.");
                                jogador = new Jorge();
                            }
                        }

                        jogador.addConsumivel("Cura", 20, 2);
                        jogador.addConsumivel("Ataque", 20, 2);
                        jogador.addConsumivel("Defesa", 20, 2);
                        jogador.addConsumivel("Velocidade", 20, 2);

                        System.out.println("Escolha a dificuldade (1 = Fácil, 2 = Médio, 3 = Difícil):");
                        int dificuldade = scanner.nextInt();
                        scanner.nextLine();
                        labirinto = new Labirinto(jogador, dificuldade);
                        jogoIniciado = true;

                        Itens teste1 = new Consumivel("Cura" ,20, 100);
                        Itens teste2 = new Consumivel("Defesa" ,20, 100);
                        Itens teste3 = new Consumivel("Ataque" ,20, 100);
                        Itens teste4 = new Consumivel("Velocidade" ,20, 100);
                        Itens teste5 = new TesouroArmadura("Armadura leve", new int[]{7, 10}, 15);
                        Itens teste6 = new TesouroArma("Espada", new int[]{1, 26}, 20);
                        Itens teste7 = new TesouroArma("Machado", new int[]{28, 46}, 25);
                        
                        labirinto.getLoja().addProduto(teste1);
                        labirinto.getLoja().addProduto(teste2);
                        labirinto.getLoja().addProduto(teste3);
                        labirinto.getLoja().addProduto(teste4);
                        labirinto.getLoja().addProduto(teste5);
                        labirinto.getLoja().addProduto(teste6);
                        labirinto.getLoja().addProduto(teste7);
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
            }
            

            while (!labirinto.verificarFim()) {
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
    
    public static void exibirMenu(Aventureiro jogador, Labirinto labirinto, Scanner scanner){ 
        int continuar = 1;
        while (continuar == 1) {
            System.out.println("===== Menu de Opções =====");
            System.out.println("Escolha uma das opções:");
            System.out.println("1 - Verificar Status");
            System.out.println("2 - Verificar Tesouros");
            System.out.println("3 - Verificar Consumiveis");
            System.out.println("4 - Salvar Jogo");
            System.out.println("5 - Voltar");
            System.out.println("6 - Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1 -> {
                    jogador.exibirStatus();
                }
                case 2 -> {
                    jogador.exibirTesouros();
                }
                case 3 -> {
                    jogador.usarConsumiveis(jogador, scanner);
                }
                case 4 -> {
                    System.out.println("Digite o nome que deseja colocar no seu save:");
                    String nome = scanner.next();
                    Save.abrirArquivo(nome, jogador, labirinto);
                }
                case 5 -> {
                    continuar = 0;
                }
                case 6 -> {
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
