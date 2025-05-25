import java.io.*;
import java.util.ArrayList;

public class Save implements Serializable {
    private String nome;
    private int id;
    private Aventureiro jogador;
    private Labirinto labirinto;

    Save(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    Save(String nome, int id, Aventureiro jogador, Labirinto labirinto) {
        this.nome = nome;
        this.id = id;
        this.jogador = jogador;
        this.labirinto = labirinto;
    }

    public String getNome() {
        return nome;
    }
    public int getId() {
        return id;
    }
    public Aventureiro getJogador() {
        return jogador;
    }
    public Labirinto getLabirinto() {
        return labirinto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setJogador(Aventureiro jogador) {
        this.jogador = jogador;
    }
    public void setLabirinto(Labirinto labirinto) {
        this.labirinto = labirinto;
    }

    public static ArrayList<Save> carregarListaSaves() {
        File arquivo = new File("Save.dat");
        if (!arquivo.exists() || arquivo.length() == 0) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) obj;
                for (Object item : lista) {
                    if (!(item instanceof Save)) {
                        System.out.println("Arquivo corrompido: item inválido na lista.");
                        return new ArrayList<>();
                    }
                }
                return (ArrayList<Save>) lista;
            } else {
                System.out.println("Formato inválido no arquivo de saves.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar saves: " + e);
        }
        return new ArrayList<>();
    }

    private static void salvarListaSaves(ArrayList<Save> saves) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Save.dat"))) {
            oos.writeObject(saves);
        } catch (IOException e) {
            System.out.println("Erro ao salvar saves: " + e);
        }
    }

    public static void abrirArquivo(String nome, Aventureiro jogador, Labirinto labirinto) {
        ArrayList<Save> saves = carregarListaSaves();

        int id = 1;
        for (Save s : saves) {
            if (s.getId() >= id) {
                id = s.getId() + 1;
            }
        }

        Save novoSave = new Save(nome, id, jogador, labirinto);
        saves.add(novoSave);

        salvarListaSaves(saves);
        System.out.println("Save salvo com sucesso com ID " + id);
    }

    public static Save lerSaves(int id) {
        ArrayList<Save> saves = carregarListaSaves();

        for (Save s : saves) {
            if (s.getId() == id) {
                return s;
            }
        }
        return new Save("Não existe", -1);
    }
}
