public class TesouroArma extends Tesouro {
    public TesouroArma(String nome, int[] localizacao, int valor) {
        super(nome, localizacao, valor);
    }

    public String getBuff(){
        return ("Ataque +" + getValor());
    }

    @Override
    public void efeito(Aventureiro jogador) {
        jogador.setAtaque(jogador.getAtaque() + getValor());
        System.out.println("Equipou arma " + getNome() + ", ataque aumentado em " + getValor());
    }

    @Override
    public void removerEfeito(Aventureiro jogador){
        jogador.setAtaque(jogador.getAtaque() - getValor());
    }
}
