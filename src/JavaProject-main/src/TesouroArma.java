public class TesouroArma extends Tesouro {
    public TesouroArma(String nome, int[] localizacao, int valor) {
        super(nome, localizacao, valor);
    }

    @Override
    public void efeito(Aventureiro jogador) {
        jogador.setAtaque(jogador.getAtaque() + getValor());
        System.out.println("Equipou arma " + getNome() + ", ataque aumentado em " + getValor());
    }
}
