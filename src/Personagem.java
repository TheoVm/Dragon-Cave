import java.util.List;
import java.util.ArrayList;

public abstract class Personagem{
    private String nome ;
    private String classe;
    private int vida;
    private int velocidade;
    private int defesa ;
    private int ataque;
    private List<Item> tesouros = new ArrayList<>();;
    private String localizacao = "a1";

    // Personagem(String nome, int vida, int velocidade, int defesa, int ataque, int tesouros, String localizacao){
    //     this.nome = nome;
    //     this.vida = vida;
    //     this.velocidade = velocidade;
    //     this.defesa = defesa;
    //     this.ataque = ataque;
    //     this.tesouros = tesouros;
    //     this.localizacao = localizacao;
    // }

    public String getNome() {
        return nome;
    }
    public String getClasse() {
        return classe;
    }
    public int getVida() {
        return vida;
    }
    public int getVelocidade() {
        return velocidade;
    }
    public int getDefesa() {
        return defesa;
    }
    public int getAtaque() {
        return ataque;
    }
    public List<Item> getTesouros() {
        return tesouros;
    }
    public String getlocalizacao() {
        return localizacao;
    }   

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setClasse(String classe) {
        this.classe = classe;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public void adicionarTesouros(Item item) {
        tesouros.add(item);
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void exibirStatus() {
        System.out.println("\nNome: " + this.nome);
        System.out.println("Classe: " + this.classe);
        System.out.println("Vida: " + this.vida);
        System.out.println("Velocidade: " + this.velocidade);
        System.out.println("Defesa: " + this.defesa);
        System.out.println("Ataque: " + this.ataque);
        System.out.println("Tesouros:");
        for (Item item : tesouros) {
            System.out.println("- " + item.getNome());
        }
        System.out.println("Localização: " + this.localizacao);
    }
}

class Tank extends Personagem{
    public Tank(String nome) {
        setNome(nome);
        setClasse("Guerreiro");
        setVida(10);
        setDefesa(8);
        setAtaque(4);
        setVelocidade(2);
    }
}

class Rogue extends Personagem{
    public Rogue(String nome) {
        setNome(nome);
        setClasse("Rogue");
        setVida(5);
        setDefesa(2);
        setAtaque(6);
        setVelocidade(9);
    }
}

class Jorge extends Personagem{
    public Jorge(String nome) {
        setNome(nome);
        setClasse("Jorge");
        setVida(7);
        setDefesa(5);
        setAtaque(6);
        setVelocidade(25);
    }
}