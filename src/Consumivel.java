public class Consumivel extends Itens {
    private int quantidade = 0;
    public Consumivel(String nome, int valor, int quantidade, int dinheiro) {
        super(nome, valor, dinheiro);
        this.quantidade = quantidade;
    }
    
    public int getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public void alterarQuantidade(int num){
        int valor = this.quantidade + num;
        setQuantidade(valor);
    }

    public static void usar(Aventureiro jogador, Consumivel consumivel){
        int novoValor = 0;
        String tipo = consumivel.getNome();
        String turnos;
        String novoTurno;
        switch (tipo) {
            case "Cura":
                novoValor = jogador.getVida() + 20;
                jogador.setVida(novoValor);
                consumivel.alterarQuantidade(-1);
                System.out.println("Pocao de Cura usada! Vida aumentada em 20!");
                break;
            case "Ataque":
                novoValor = jogador.getAtaque() + 10;
                jogador.setAtaque(novoValor);
                turnos = jogador.getEfeitos().get(0)[1];
                novoTurno = String.valueOf(Integer.parseInt(turnos) + 3);
                jogador.setEfeito(novoTurno, 0);
                consumivel.alterarQuantidade(-1);
                System.out.println("Pocao de Ataque usada! Ataque aumentada em 20!");
                break;
            case "Defesa":
                novoValor = jogador.getDefesa() + 10;
                jogador.setDefesa(novoValor);
                turnos = jogador.getEfeitos().get(1)[1];
                novoTurno = String.valueOf(Integer.parseInt(turnos) + 3);
                jogador.setEfeito(novoTurno, 0);
                consumivel.alterarQuantidade(-1);
                System.out.println("Pocao de Defesa usada! Velocidade aumentada em 20!");
                break;
            case "Velocidade":
                novoValor = jogador.getVelocidade() + 10;
                jogador.setVelocidade(novoValor);
                turnos = jogador.getEfeitos().get(2)[1];
                novoTurno = String.valueOf(Integer.parseInt(turnos) + 3);
                jogador.setEfeito(novoTurno, 0);
                consumivel.alterarQuantidade(-1);
                System.out.println("Pocao de Velocidade usada! Velocidade aumentada em 20!");
                break;
            default:
                break;
        }
    }

}