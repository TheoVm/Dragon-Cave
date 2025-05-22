import java.io.*;

class AppendOOS extends ObjectOutputStream {
    AppendOOS(OutputStream out) throws IOException{
        super(out);
    }
    @Override protected void writeStreamHeader() {
    }
}