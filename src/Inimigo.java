import java.io.Serializable;

public class Inimigo implements Serializable{ 
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    private int valor;
    private int[] localizacao;
    
    public Inimigo(String nome, int vida, int ataque, int defesa, int valor) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.valor = valor;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
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
    public int getValor(){
        return valor;
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
    public void setValor(int valor){
        this.valor = valor;
    }

    
}