public class Item{
    private String nome;
    private int ataque;
    private int defesa;
    private int penalidade;

    Item(String nome, int ataque, int defesa, int penalidade){
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.penalidade = penalidade;
    }

    public String getNome(){
        return nome;
    }
    public int getAtaque(){
        return ataque;
    }
    public int getDefesa(){
        return defesa;
    }
    public int getPenalidade(){
        return penalidade;
    }

    public void setNome(String nome) {
    this.nome = nome;
    }
    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public void setPenalidade(int penalidade) {
        this.penalidade = penalidade;
    }
}