import java.io.Serializable;
import java.util.*;

public abstract class Aventureiro implements Serializable{
    private final String nome;
    private int vida;
    private int vidaMAX;
    private int velocidade;
    private int defesa;
    private int ataque;
    private int ouro;
    private boolean chave;
    private List<String[]> efeitos;
    private final List<Tesouro> tesouros;
    private List<Consumivel> consumiveis;
    private List<Diario> diarios;
    private int[] localizacao;

    public Aventureiro(String nome, int vida, int velocidade, int defesa, int ataque) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMAX = vida;
        this.velocidade = velocidade;
        this.defesa = defesa;
        this.ataque = ataque;
        this.ouro = 5000;
        this.tesouros = new ArrayList<>();
        this.consumiveis = new ArrayList<>();
        this.diarios = new ArrayList<>();
        this.localizacao = new int[]{1, 1};
        this.efeitos = new ArrayList<>(Arrays.asList(new String[] {"Ataque", "0"}, new String[] {"Defesa", "0"}, new String[] {"Velocidade", "0"}));
    }

    String arte = """
                   ............                      
                    ...:#==-*:. .                    
                    .:+*#*++*+*-...                  
                   ..++%*#+++%+%....                 
                   ..:**%***#+*:....                 
                   ....:#++=*-.....                  
                      ..%%%%%:.                      
                       .%##*%:.                      
                       .%%%%%:.                      
                       .%%%#%:.                      
                    ....%##*%:.                      
                   .....%%%#%:.                      
                   ....:#%%%%-. .                    
         ....----------#+++++%----------......       
         ...#:-=+=----==-:::-===---=*=-:#.....       
          ..#===*######*=:::-*#######===#.....       
          ...===:.....**##***+# .....===:..          
            ......  ..*+++-==-# .  ........          
                    ..*++=----# .                    
                    ..*++=----# .                    
                    ..*++=----#..                    
                    ..*++=----#..                    
                    . *++=----# .                    
                    . *+-:----# .                    
                    ..*===----# .                    
                    ..*+=:----# .                    
                    ..*===----# .                    
                    ..*++=----# .                    
                    ..*++=----#..                    
                    ..*++=---:#..                    
                   ...*++=----# .                    
                    ..*+=-----# .                    
         .............*=-=----%======......          
         ....+++++++++%++=----%======#*:...          
     ....****=========#*+=---=%========***=...       
    ..*##+===+++++++++%**+-=++%+++++++++===+##....   
  ...+#*+++===+=======+*%%#%%*+=======+====+###*..   
  ..=#+++++++==========+++++++=========+*#######:..  
....+#+++++++++=======================*##########..  
....**+++++++++++================+++*###########%=...
...:#++++++++++++++++===+++++********############@=..
..:#++++++++++++++++++++**************###########%#..
..:@+++++++++++++++++++***************##########%%=..
..-@++++++++++++++++++*****************########%*..  
..#**++++++++++++++++++****************########...   
..:*##++++++++++++====+****************#####*:....   
   ..:#####+++=======+******************###-..       
     ..........=%+===+****************#@%..          
     ..............%*****@@@@@@@@=...........        
                 ..................                                                                  
            """;

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
            || "┘".equals(aux[novaX][novaY])
            || "┬".equals(aux[novaX][novaY]) 
            || "┴".equals(aux[novaX][novaY]) 
            || "┤".equals(aux[novaX][novaY]) 
            || "├".equals(aux[novaX][novaY]) ) {
            System.out.println("Movimento inválido! Fora dos limites do labirinto.");
            return;
        }

        localizacao[0] = novaX;
        localizacao[1] = novaY;

        for(int i = 0; i < labirinto.getDiarios().size(); i++){
            Diario diario = labirinto.getDiarios().get(i);
            if (Arrays.equals(diario.getLocalizacao(), localizacao)){
                System.out.println("add");
                JogoLabirinto.printComDelay("Diário encontrado: " + diario.getNome(), 20);
                adicionarDiario(diario);
            }
        }

        for (Iterator<Tesouro> it = labirinto.getTesouros().iterator(); it.hasNext();) {
            Tesouro t = it.next();
            if (Arrays.equals(t.getLocalizacao(), localizacao)) {
                if(t.getNome().equals("Excalibur")){
                    JogoLabirinto.limparTela();
                    System.out.println(arte);

                    JogoLabirinto.printComDelay("Você encontrou a lendária espada Excalibur!", 50);
                    JogoLabirinto.printComDelay("Você tenta puxar ela mas nada acontece.", 50);
                    JogoLabirinto.printComDelay("E como ela já está sendo usada em outros jogos, ela some na sua frente sem deixar vestígios", 20);

                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    it.remove();
                } else {
                    JogoLabirinto.printComDelay("Tesouro encontrado: " + t.getNome(), 20);
                    t.efeito(this);
                    tesouros.add(t);
                    it.remove();
                }
            }
        }
        
        for (int i = 0; i < labirinto.getPosicoes().size(); i++){
            int[] posicao = labirinto.getPosicoes().get(i);
            if (posicao[0] == this.localizacao[0] && posicao[1] == this.localizacao[1] && labirinto.getInimigosGerados().get(i).getVida() > 0) {
                boolean venceu;
                if(labirinto.getTipo() == 4){
                    venceu = CombateBoss.iniciarCombate(this, labirinto.getInimigosGerados().get(i), scanner);
                } else {
                    venceu = Combate.iniciarCombate(this, labirinto.getInimigosGerados().get(i), scanner);
                }
                if (!venceu) {
                    if(vida > 0){
                        System.out.println("Você fugiu do combate!");
                    } else {
                        JogoLabirinto.printComDelay("Você morreu...", 400);
                        JogoLabirinto.printComDelay("┌──────────────────────────────────────────────────────────────────────────────────┐", 1);
                        JogoLabirinto.printComDelay("│                                                                                  │", 1);
                        JogoLabirinto.printComDelay("│    ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗      │", 1);
                        JogoLabirinto.printComDelay("│    ██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗    │", 1);
                        JogoLabirinto.printComDelay("│    ██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝    │", 1);
                        JogoLabirinto.printComDelay("│    ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗    │", 1);
                        JogoLabirinto.printComDelay("│    ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║    │", 1);
                        JogoLabirinto.printComDelay("│    ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝     │", 1);
                        JogoLabirinto.printComDelay("│                                                                                  │", 1);
                        JogoLabirinto.printComDelay("└──────────────────────────────────────────────────────────────────────────────────┘", 1);
                        
                        System.exit(0);
                        }
                } else {
                    System.out.println("Você venceu o combate!");
                }
            }
        }
        if(Arrays.equals(labirinto.getLoja().getLocalizacao(), localizacao)){
            System.out.println("Achou a loja!");
            labirinto.getLoja().menuLoja(this, scanner);
        }
        
        if(Arrays.equals(labirinto.getFim(), localizacao)){
            if (this.chave){
                int continuar = 1;
                while (continuar == 1) {
                    System.out.println("Você possuí a chave para o próximo andar, deseja avançar? (Após avançar não será possível voltar a este andar)");
                    System.out.println("1 - Avançar");
                    System.out.println("2 - Continuar no andar");
                    int escolha = scanner.nextInt();
                    scanner.nextLine();
                    switch (escolha) {
                        case 1:
                            labirinto.setTrocar(true);
                            continuar = 0;
                            break;
                        case 2: 
                            continuar = 0;
                            break;
                        default: 
                            System.out.println("Opção inválida.");
                    }
                    
                }
            } else {
                System.out.println("Você se depara com uma grande porta e um mecanismo com alguma espécie de fechadura, uma chave deve ser necessária para abri-la e seguir adiante");
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMAX() {
        return vidaMAX;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVida(int vida) {
        if (vida < 0){
            vida = 0;
        }
        if (vida > vidaMAX){
            vida = vidaMAX;
            System.out.println("Sua vida está no máximo!");
        }
        this.vida = vida;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getAtaque() {
        return ataque;
    }
    
    public int getOuro() {
        return ouro;
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

    public List<Diario> getDiarios() {
        return diarios;
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

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public void possuiChave(boolean possui){
        this.chave = possui;
    }

    public void setLocalizacao(int[] localizacao) {
        this.localizacao = localizacao;
    }

    public void adicionarDiario(Diario diario){
        diarios.add(diario);
    }

    public void addConsumivel(String nome, int valor, int quantidade, int dinheiro){
        int criar = 0;
        for (int i = 0; i < this.consumiveis.size(); i++) {
            if (consumiveis.get(i).getNome().equals(nome)){
                consumiveis.get(i).alterarQuantidade(1);
                criar = 1;
            }
        }
        if (criar == 0){
            Consumivel consumivel = new Consumivel(nome, valor, quantidade, dinheiro);
            consumiveis.add(consumivel);
        }
    }

    public void exibirStatus(){
        System.out.println("\nNome: "+ this.nome);
        System.out.println("Vida: "+ this.vida);
        System.out.println("Velocidade: "+ this.velocidade);
        System.out.println("Defesa: "+ this.defesa);
        System.out.println("Ataque: "+ this.ataque);
        System.out.println("Ouro: $"+ this.ouro);
        if(this.chave){
            System.out.println("Possuí chave");
        } else {
            System.out.println("Não possuí chave");
        }
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

    public void exibirDiarios(Aventureiro jogador, Scanner scanner){
        if (this.diarios.isEmpty()){
            System.out.println("Voce nao tem diarios no momento");
        } else {
            for(int i = 0; i < this.diarios.size(); i++){
                Diario diario = this.diarios.get(i);
                System.out.println((i + 1) + " - " + diario.getNome() + " (" + diario.getData() + ")");
            }
            int continuar = 1;
            while (continuar == 1) {
                try {
                    System.out.println("Qual diario deseja ler?");
                    int escolha = scanner.nextInt();
                    scanner.nextLine();
                    if (escolha < 1 || escolha > this.diarios.size()) {
                        System.out.println("Opção inválida.");
                    } else {
                        Diario d = this.diarios.get(escolha - 1);
                        JogoLabirinto.printComDelay("Conteúdo do diário: " + d.getConteudo(), 20);
                        continuar = 0;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    scanner.nextLine();
                }
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
            int continuar = 1;
                while (continuar == 1) {
                    System.out.println("===== Menu de Consumíveis =====");
                    System.out.println("Consumiveis: ");
                    for (int i = 0; i < this.consumiveis.size(); i++) {
                        System.out.println((i + 1) + " - Poção de " + this.consumiveis.get(i).getNome() + " x" + this.consumiveis.get(i).getQuantidade());
                    }
                    System.out.println("\nEscolha uma das opções:");
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
