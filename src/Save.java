import java.io.*;

public class Save implements Serializable{
    private String nome;
    private int id;
    private Aventureiro jogador;
    private Labirinto labirinto;

    Save(String nome, int id, Aventureiro jogador, Labirinto labirinto){
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
    public Aventureiro getJogador(){
        return jogador;
    }
    public Labirinto getLabirinto(){
        return labirinto;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setJogador(Aventureiro jogador){
        this.jogador = jogador;
    }
    public void setLabirinto(Labirinto labirinto){
        this.labirinto = labirinto;
    }

    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }
        @Override
        protected void writeStreamHeader() throws IOException {
        }
    }


    public static void abrirArquivo(String nome, Aventureiro jogador, Labirinto labirinto) {
        File arquivo = new File("Saves.dat");
        int id = 1;

        if (arquivo.exists() && arquivo.length() > 0) {
            try (ObjectInputStream ois = 
                     new ObjectInputStream(new FileInputStream(arquivo))) {
                while (true) {
                    try {
                        ois.readObject();
                        id++;
                    } catch (EOFException eof) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao ler arquivo: " + e);
                return;
            }
        }

        Save save = new Save(nome, id, jogador, labirinto);

        boolean append = arquivo.exists() && arquivo.length() > 0;
        try (FileOutputStream fos = new FileOutputStream(arquivo, true);
             ObjectOutputStream oos = append
                 ? new AppendableObjectOutputStream(fos)
                 : new ObjectOutputStream(fos)
        ) {
            oos.writeObject(save);
        } catch (IOException e) {
            System.out.println("Erro ao criar / escrever arquivo: " + e);
        }
    }

    public static void lerSaves(){
        try{
            FileInputStream arq = new FileInputStream("Saves.dat");
            ObjectInputStream obj = new ObjectInputStream(arq);
            while(true){
                try{
                    Object object = obj.readObject();
                    if(object instanceof Save){
                        Save exibir = (Save)object;
                        System.out.println("Nome: "+ exibir.getNome());
                        System.out.println("ID: "+ exibir.getId());
                    }
                }catch(EOFException eof){
                    break;
                }
            }
            obj.close();
        } catch (IOException | ClassNotFoundException erro){
            System.out.println("Erro ao ler arquivo:" + erro);
        }
    }
}