import java.io.Serializable;

public class Inimigo implements Serializable{ 
    private int vida;
    private int ataque;
    private int defesa;
    private int[] localizacao;
    
    public Inimigo(int vida, int ataque, int defesa, int[] localizacao) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.localizacao = localizacao;
    }

    //getters e setters
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public int getDefesa() {
        return defesa;
    }
    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
    public int[] getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(int[] localizacao) {
        this.localizacao = localizacao;
    }
}