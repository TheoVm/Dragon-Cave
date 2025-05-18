public class App {
    public static void main(String[] args) throws Exception {
        Personagem char1 = new Tank("Felipe");
        Personagem char2 = new Rogue("Chezd");
        Personagem char3 = new Jorge("Jorge");
        Item adaga = new Item("Agada", 2, 3, 4);
        Item machado = new Item("Machado", 3, 2, 6);
        Item espada = new Item("Espada", 3, 2, 6);
        char1.adicionarTesouros(machado);
        char2.adicionarTesouros(adaga);
        char3.adicionarTesouros(espada);
        char1.exibirStatus();
        char2.exibirStatus();
        char3.exibirStatus();
    }
}
