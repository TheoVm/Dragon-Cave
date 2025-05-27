public class TesouroArmadura extends Tesouro {
    public TesouroArmadura(String nome, int[] localizacao, int valor) {
        super(nome, localizacao, valor);
    }

    public String getBuff(){
        return ("Defesa +" + getValor());
    }

    @Override
    public void efeito(Aventureiro jogador) {
        jogador.setDefesa(jogador.getDefesa() + getValor());
        System.out.println("Equipou armadura " + getNome() + ", defesa aumentada em " + getValor());
    }
}
