public class TesouroArmadura extends Tesouro {
    public TesouroArmadura(String nome, int[] localizacao, int valor, int dinheiro) {
        super(nome, localizacao, valor, dinheiro);
    }

    public String getBuff(){
        return ("Defesa +" + getValor());
    }

    @Override
    public void efeito(Aventureiro jogador) {
        jogador.setDefesa(jogador.getDefesa() + getValor());
        System.out.println("Equipou armadura " + getNome() + ", defesa aumentada em " + getValor());
    }
    
    @Override
    public void removerEfeito(Aventureiro jogador){
        jogador.setDefesa(jogador.getDefesa() - getValor());
    }
}
