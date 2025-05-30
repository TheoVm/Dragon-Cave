import java.io.Serializable;
import java.util.*;

public class Loja implements Serializable{
    private final List<Itens> produtos;
    private int[] localizacao;

    Loja(int[] localizacao){
        this.produtos = new ArrayList<>();
        this.localizacao = localizacao;
        Itens porduto1 = new Consumivel("Cura" ,20, 1, 20);
        Itens porduto2 = new Consumivel("Defesa" ,20, 1, 20);
        Itens porduto3 = new Consumivel("Ataque" ,20, 1, 20);
        Itens porduto4 = new Consumivel("Velocidade" ,20, 1, 20);
        Itens porduto5 = new TesouroArmadura("Essência de Inter", new int[]{7, 10}, 2, 80);
        Itens porduto6 = new TesouroArma("Garra de Inter Esquecido", new int[]{1, 26}, 2, 80);
        Itens porduto7 = new TesouroArma("Machado", new int[]{28, 46}, 2, 80);
        Itens porduto8 = new Chave("Chave", 160, 1, 160);
        
        addProduto(porduto1);
        addProduto(porduto2);
        addProduto(porduto3);
        addProduto(porduto4);
        addProduto(porduto5);
        addProduto(porduto6);
        addProduto(porduto7);
        addProduto(porduto8);
    }

    public List<Itens> getProdutos(){
        return produtos;
    }

    public void addProduto(Itens item){
        this.produtos.add(item);
    }

    public int[] getLocalizacao(){
        return localizacao;
    }



    public void setPosicao(int[] localizacao){
        this.localizacao = localizacao;
    }

    public void menuLoja(Aventureiro jogador, Scanner scanner){
        int continuar = 1;
        while (continuar == 1) {
            System.out.println("======== BEM VINDO A LOJA ========");
            System.out.println("Escolha uma das opções:");
            System.out.println("1 - Comprar");
            System.out.println("2 - Vender");
            System.out.println("3 - Conversar");
            System.out.println("4 - Voltar");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1:
                    comprar(jogador, scanner);
                    break;
                case 2:
                    vender(jogador, scanner);
                    break;
                case 3:
                        int continuarConversa = 1;
                            boolean primeiraVez = true;
                while (continuarConversa == 1) {
                    if (primeiraVez) {
                        printComDelay("Hm? Você deseja conversar comigo? Muito bem, o que deseja saber?", 30);
                        primeiraVez = false;
                    }
                    System.out.println("1 - Como você conseguiu estabelecer uma loja aqui?");
                    System.out.println("2 - Por que os Dragonites não te atacam?");
                    System.out.println("3 - O que você sabe sobre esse lugar?");
                    System.out.println("4 - Voltar");
                    
                    int pergunta = scanner.nextInt();
                    scanner.nextLine();
                    switch(pergunta){
                        case 1:
                            printComDelay("Naumti", 30);
                            System.out.println("1 - ?");
                            System.out.println("2 - ??");
                            System.out.println("3 - Voltar");
                            int subPergunta = scanner.nextInt();
                            scanner.nextLine();
                            switch(subPergunta){
                                case 1:
                                    printComDelay ("Naumtiressa, otário", 30);
                                    System.out.println();
                                    break;
                                case 2:
                                    printComDelay ("Naumtiressa, otário", 30);
                                    System.out.println();
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }
                            break;
                        case 2:
                            printComDelay("Antes de entrar nesse lugar eu sempre tomo uma poção que apaga por completo minha presença. E não, ela não está a venda", 10);        
                            System.out.println();                
                            break;
                        case 3:
                            printComDelay ("Pouco se sabe sobre a caverna dos Dragonites. Soube de um aventureiro que ousou se aventurar por aqui e compartilhou suas experiências em seu diário particular.", 10);

                            System.out.println("1 - Você sabe onde posso encontrar esse diário?");
                            System.out.println("2 - Voltar");
                            System.out.println();

                            int subPergunta2 = scanner.nextInt();
                            scanner.nextLine();
                            switch(subPergunta2){
                                case 1:
                                    printComDelay("Dizem as lendas que páginas perdidas estão espalhadas pela caverna...", 10);
                                    System.out.println();
                                    break;
                                case 2:
                                    // Voltar
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }
                            break;
                        case 4:
                            continuarConversa = 0;
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                }
                case 4:
                    continuar = 0;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
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

    public void comprar(Aventureiro jogador, Scanner scanner){
        System.out.println("======== ITENS A VENDA ========");
        exibirProdutos();
        System.out.println("\nOuro atual: $"+jogador.getOuro());
        System.out.println("\nQual produto deseja comprar?");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        if(escolha != 0){
            if (jogador.getOuro() < produtos.get(escolha - 1).getDinheiro()){
                System.out.println("Você não tem ouro o suficiente para comprar esse item");
            } else {
                jogador.setOuro((jogador.getOuro() - produtos.get(escolha - 1).getDinheiro()));
                if (produtos.get(escolha - 1) instanceof Consumivel){
                    jogador.addConsumivel(produtos.get(escolha - 1).getNome(), produtos.get(escolha - 1).getValor(), 1, produtos.get(escolha -1).getDinheiro());
                } else {
                    if (produtos.get(escolha - 1) instanceof TesouroArma){
                        TesouroArma item = new TesouroArma(produtos.get(escolha - 1).getNome(), new int[]{0, 0}, produtos.get(escolha - 1).getValor(), produtos.get(escolha -1).getDinheiro());
                        jogador.getTesouros().add(item);
                        item.efeito(jogador);
                    } else if (produtos.get(escolha - 1) instanceof TesouroArmadura){
                        TesouroArmadura item = new TesouroArmadura(produtos.get(escolha - 1).getNome(), new int[]{0, 0}, produtos.get(escolha - 1).getValor(), produtos.get(escolha -1).getDinheiro());
                        jogador.getTesouros().add(item);
                        item.efeito(jogador);
                    } else if (produtos.get(escolha - 1) instanceof Chave){
                        jogador.possuiChave(true);
                    }
                }
                System.out.println("\nItem Comprado Com Sucesso!");
            }
        } 
    }

    public void exibirProdutos(){
        System.out.println("0 - Sair");
        for(int i = 0; i < produtos.size(); i++){
            if(produtos.get(i) instanceof Consumivel){
                System.out.println(i+1 + " - Poção de " + produtos.get(i).getNome() + " $" + produtos.get(i).getDinheiro());
            } else {
                if(produtos.get(i) instanceof TesouroArma){
                    TesouroArma item = new TesouroArma(produtos.get(i).getNome(), new int[]{}, produtos.get(i).getValor(), produtos.get(i).getDinheiro());
                    System.out.println(i+1 + " - " + item.getNome() + " (" + item.getBuff() + ")" + " $" + item.getDinheiro());
                } else if(produtos.get(i) instanceof TesouroArmadura){
                    TesouroArmadura item = new TesouroArmadura(produtos.get(i).getNome(), new int[]{}, produtos.get(i).getValor(), produtos.get(i).getDinheiro());
                    System.out.println(i+1 + " - " + item.getNome() + " (" + item.getBuff() + ")" + " $" + item.getDinheiro());
                } else {
                    Chave item = new Chave(produtos.get(i).getNome(), produtos.get(i).getValor(), 1, produtos.get(i).getDinheiro());
                    System.out.println(i+1 + " - " + item.getNome() + " $" + item.getDinheiro());
                } 
            }
        }
    }

    public void vender(Aventureiro jogador, Scanner scanner){
        System.out.println("======== ITENS PARA VENDER ========");
        List<Itens> inventario = new ArrayList<>();
        int index = 1;

        System.out.println("---- Consumiveis ----");
        List<Consumivel> consumiveis = jogador.getConsumiveis();
        for (Consumivel c : consumiveis) {
            System.out.println(index + " - " + c.getNome() + " (x" + c.getQuantidade() + ") - Valor: " + c.getDinheiro());
            inventario.add(c);
            index++;
        }

        System.out.println("---- Tesouros ----");
        List<Tesouro> tesouros = jogador.getTesouros();
        if(!tesouros.isEmpty()){
            for (Tesouro t : tesouros) {
                System.out.println(index + " - " + t.getNome() + " " + t.getBuff() + " - Valor: " + t.getDinheiro());
                inventario.add(t);
                index++;
            }
        } else {
            System.out.println("Você não possuí tesouros no momento");
        }
            

        System.out.println("0 - Sair");
        System.out.println("Qual item deseja vender?");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha != 0 && escolha <= inventario.size()) {
            Itens item = inventario.get(escolha - 1);
            jogador.setOuro(jogador.getOuro() + item.getDinheiro());

            if (item instanceof Consumivel) {
                Consumivel c = (Consumivel) item;
                c.setQuantidade(c.getQuantidade() - 1);
                if (c.getQuantidade() <= 0) {
                    consumiveis.remove(c);
                }
            } else {
                Tesouro t = (Tesouro) item;
                t.removerEfeito(jogador);
                tesouros.remove(item);
            }

            System.out.println("Item vendido por " + item.getDinheiro() + " ouro.");
        } else if (escolha != 0) {
            System.out.println("Escolha invalida.");
        }
    }


}
