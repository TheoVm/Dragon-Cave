public class Personagem{
    private String nome = "jorge";
    private int vida = 5;
    private int velocidade = 5;
    private int defesa = 5;
    private int ataque = 5;
    private int tesouros = 2;
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
public int getTesouros() {
    return tesouros;
}

public String getlocalizacao() {
    return nome;
}
    
public void setNome(String nome) {
    this.nome = nome;
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

public void setTesouros(int tesouros) {
    this.tesouros = tesouros;
}

public void setLocalizacao(String localizacao) {
    this.localizacao = localizacao;
}

public void exibirStatus(){
    System.out.println(this.nome);
    System.out.println(this.vida);
    System.out.println(this.velocidade);
    System.out.println(this.defesa);
    System.out.println(this.ataque);
    System.out.println(this.tesouros);
    System.out.println(this.localizacao);
}
}