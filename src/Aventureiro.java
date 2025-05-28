import java.io.Serializable;
import java.util.*;

public abstract class Aventureiro implements Serializable{
    private final String nome;
    private int vida;
    private int velocidade;
    private int defesa;
    private int ataque;
    private List<String[]> efeitos;
    private final List<Tesouro> tesouros;
    private List<Consumivel> consumiveis;
    private int[] localizacao;

    public Aventureiro(String nome, int vida, int velocidade, int defesa, int ataque) {
        this.nome = nome;
        this.vida = vida;
        this.velocidade = velocidade;
        this.defesa = defesa;
        this.ataque = ataque;
        this.tesouros = new ArrayList<>();
        this.consumiveis = new ArrayList<>();
        this.localizacao = new int[]{1, 1};
        this.efeitos = new ArrayList<>(Arrays.asList(new String[] {"Ataque", "0"}, new String[] {"Defesa", "0"}, new String[] {"Velocidade", "0"}));
    }

    public void mover(int dx, int dy, Labirinto labirinto, Scanner scanner) {
        int novaX = localizacao[0] + dx;
        int novaY = localizacao[1] + dy;

        String[][] aux = labirinto.getMapa();

        if (novaX < 0 || novaY < 0 || novaX >= labirinto.getAltura() || novaY >= labirinto.getLargura()
            || "─".equals(aux[novaX][novaY])
            || "│".equals(aux[novaX][novaY])
            || "┌".equals(aux[novaX][novaY])
            || "┐".equals(aux[novaX][novaY])
            || "└".equals(aux[novaX][novaY]) 
            || "┘".equals(aux[novaX][novaY])) {
            System.out.println("Movimento inválido! Fora dos limites do labirinto.");
            return;
        }

        localizacao[0] = novaX;
        localizacao[1] = novaY;

        for (Iterator<Tesouro> it = labirinto.getTesouros().iterator(); it.hasNext();) {
            Tesouro t = it.next();
            if (Arrays.equals(t.getLocalizacao(), localizacao)) {
                System.out.println("Tesouro encontrado: " + t.getNome());
                t.efeito(this);
                tesouros.add(t);
                it.remove();
            }
        }

        for (Perigo p : labirinto.getPerigos()) {
            if (Arrays.equals(p.getLocalizacao(), localizacao)) {
                int dano = Math.max(0, p.getDano() - defesa);
                vida -= dano;
                System.out.println("Você entrou em um perigo! Sofreu " + dano + " de dano. Vida restante: " + vida);
            }
        }
        for (int i = 0; i < labirinto.posicoesInimigos().size(); i++){
            if (Arrays.equals(labirinto.posicoesInimigos().get(i), localizacao) && labirinto.getInimigosGerados().get(i).getVida() > 0) {
                System.out.println(labirinto.posicoesInimigos().get(i)[0]);
                boolean venceu = Combate.iniciarCombate(this, labirinto.getInimigosGerados().get(i), scanner);
                if (!venceu) {
                    System.out.println("Você perdeu ou fugiu do combate!");
                } else {
                    System.out.println("Você venceu o combate!");
                }
                return;
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVida(int vida) {
        if (vida < 0){
            vida = 0;
        }
        this.vida = vida;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getAtaque() {
        return ataque;
    }

    public List<Tesouro> getTesouros() {
        return tesouros;
    }

    public List<Consumivel> getConsumiveis() {
        return consumiveis;
    }
    
    public List<String[]> getEfeitos() {
        return efeitos;
    }

    public int[] getLocalizacao() {
        return localizacao;
    }

    public void setEfeito(String novoTurno, int i){
        this.efeitos.get(i)[1] = novoTurno;
    }

    public void setAtaque(int ataque) {
        
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }


    public void setLocalizacao(int[] localizacao) {
        this.localizacao = localizacao;
    }

    public void addConsumivel(int valor, int quantidade, String tipo){
        int criar = 0;
        for (int i = 0; i < this.consumiveis.size(); i++) {
            if (consumiveis.get(i).getTipo().equals(tipo)){
                consumiveis.get(i).alterarQuantidade(1);
                criar = 1;
            }
        }
        if (criar == 0){
            Consumivel consumivel = new Consumivel(valor, quantidade, tipo);
            consumiveis.add(consumivel);
        }
    }

    // public int atacar(Inimigo inimigo) {
    //     int dano = Math.max(0, this.getAtaque() - inimigo.getDefesa());
    //     inimigo.setVida(inimigo.getVida() - dano);
    //     return dano;
    // }

    public void exibirStatus(){
        System.out.println("Nome: "+ this.nome);
        System.out.println("Vida: "+ this.vida);
        System.out.println("Velocidade: "+ this.velocidade);
        System.out.println("Defesa: "+ this.defesa);
        System.out.println("Ataque: "+ this.ataque);
    }

    public void exibirTesouros(){
        if (this.tesouros.isEmpty()){
            System.out.println("Voce nao tem tesouros no momento");
        } else {
            System.out.println("Tesouros: ");
            for (Tesouro tesouro : this.tesouros){
                System.out.println("- " + tesouro.getNome() + ": " + tesouro.getBuff());
            }
        }
    }

    public void verificarQuantidade(){
        for (int i = 0; i < this.consumiveis.size(); i++) {
            if (consumiveis.get(i).getQuantidade() <= 0){
                consumiveis.remove(i);
            }
        }
    }

    public void usarConsumiveis(Aventureiro joogador, Scanner scanner){
        if (this.consumiveis.isEmpty()){
            System.out.println("Voce nao tem consumiveis no momento");
        } else {
            System.out.println("Consumiveis: ");
            for (int i = 0; i < this.consumiveis.size(); i++) {
                System.out.println((i + 1) + " - " + this.consumiveis.get(i).getTipo() + " x" + this.consumiveis.get(i).getQuantidade());
            }

            int continuar = 1;
                while (continuar == 1) {
                    System.out.println("===== Menu de Opções =====");
                    System.out.println("Escolha uma das opções:");
                    System.out.println("1 - Usar consumível");
                    System.out.println("2 - Voltar");
                    int escolha = scanner.nextInt();
                    scanner.nextLine();
                    switch (escolha) {
                        case 1 -> { 
                            System.out.println("Qual consumivel deseja utilizar?");
                            int item = scanner.nextInt();
                            scanner.nextLine();
                            Consumivel.usar(joogador, this.consumiveis.get(item - 1));
                            verificarQuantidade();
                        }
                        case 2 -> {
                            continuar = 0;
                        }
                        default -> System.out.println("Opção inválida.");
                    }
                }

        }
        verificarQuantidade();
    } 
}
