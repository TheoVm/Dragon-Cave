import java.util.*;

public class Loja {
    private final List<Itens> produtos;
    private int[] localizacao;

    Loja(int[] localizacao){
        this.produtos = new ArrayList<>();
        this.localizacao = localizacao;
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
            System.out.println("3 - Voltar");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1 -> {
                    comprar(jogador, scanner);
                }
                case 2 -> {
                    vender(jogador, scanner);
                }
                case 3 -> {
                    continuar = 0;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public void comprar(Aventureiro jogador, Scanner scanner){
        System.out.println("======== ITENS A VENDA ========");
        System.out.println("0 - Sair");
        for(int i = 0; i < produtos.size(); i++){
            System.out.println(i+1 + "- " + produtos.get(i).getNome());

        }
        System.out.println("Qual produto deseja comprar?");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        if(escolha != 0){
            if (jogador.getOuro() < produtos.get(escolha - 1).getValor()){
                System.out.println("Você não tem ouro o suficiente para comprar esse item");
            } else {
                jogador.setOuro((jogador.getOuro() - produtos.get(escolha - 1).getValor()));
                if (produtos.get(escolha - 1) instanceof Consumivel){
                    jogador.addConsumivel(produtos.get(escolha - 1).getNome(), produtos.get(escolha - 1).getValor(), 1);
                } else {
                    if (produtos.get(escolha - 1) instanceof TesouroArma){
                        TesouroArma item = new TesouroArma(produtos.get(escolha - 1).getNome(), new int[]{0, 0}, produtos.get(escolha - 1).getValor());
                        jogador.getTesouros().add(item);
                        item.efeito(jogador);
                    } else {
                        TesouroArmadura item = new TesouroArmadura(produtos.get(escolha - 1).getNome(), new int[]{0, 0}, produtos.get(escolha - 1).getValor());
                        jogador.getTesouros().add(item);
                        item.efeito(jogador);
                    }
                    
                    
                }
            }
        } 
    }

    public void vender(Aventureiro jogador, Scanner scanner){
        System.out.println("======== ITENS PARA VENDER ========");
        List<Itens> inventario = new ArrayList<>();
        int index = 1;

        System.out.println("---- Consumiveis ----");
        List<Consumivel> consumiveis = jogador.getConsumiveis(); // Supondo que existe esse método
        for (Consumivel c : consumiveis) {
            System.out.println(index + " - " + c.getNome() + " (x" + c.getQuantidade() + ") - Valor: " + c.getValor());
            inventario.add(c);
            index++;
        }

        System.out.println("---- Tesouros ----");
        List<Tesouro> tesouros = jogador.getTesouros();
        if(!tesouros.isEmpty()){
            for (Tesouro t : tesouros) {
                System.out.println(index + " - " + t.getNome() + " - Valor: " + t.getValor());
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
            jogador.setOuro(jogador.getOuro() + item.getValor());

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

            System.out.println("Item vendido por " + item.getValor() + " ouro.");
        } else if (escolha != 0) {
            System.out.println("Escolha invalida.");
        }
    }


}
