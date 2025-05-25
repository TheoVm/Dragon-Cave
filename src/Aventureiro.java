import java.io.Serializable;
import java.util.*;

public abstract class Aventureiro implements Serializable{
    private final String nome;
    private int vida;
    private int velocidade;
    private int defesa;
    private int ataque;
    private final List<Tesouro> tesouros;
    private int[] localizacao;

    public Aventureiro(String nome, int vida, int velocidade, int defesa, int ataque) {
        this.nome = nome;
        this.vida = vida;
        this.velocidade = velocidade;
        this.defesa = defesa;
        this.ataque = ataque;
        this.tesouros = new ArrayList<>();
        this.localizacao = new int[]{1, 1};
    }

    // Agora recebe Scanner como parâmetro!
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
        for (Inimigo inimigo : labirinto.getInimigos()) {
            if (Arrays.equals(inimigo.getLocalizacao(), localizacao) && inimigo.getVida() > 0) {
                boolean venceu = Combate.iniciarCombate(this, inimigo, scanner);
                if (!venceu) {
                    System.out.println("Você perdeu ou fugiu do combate!");
                    return;
                }
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

    public int[] getLocalizacao() {
        return localizacao;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setLocalizacao(int[] localizacao) {
        this.localizacao = localizacao;
    }

    public int atacar(Inimigo inimigo) {
        int dano = Math.max(0, this.getAtaque() - inimigo.getDefesa());
        inimigo.setVida(inimigo.getVida() - dano);
        return dano;
    }

    public void exibirStatus(){
        System.out.println("Nome: "+ this.nome);
        System.out.println("Vida: "+ this.vida);
        System.out.println("Velocidade: "+ this.velocidade);
        System.out.println("Defesa: "+ this.defesa);
        System.out.println("Ataque: "+ this.ataque);
        System.out.println("Tesouros: ");
        for (Tesouro tesouro : this.tesouros){
            System.out.println("- " + tesouro);
        }
    }
}