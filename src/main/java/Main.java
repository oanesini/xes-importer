import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;



public class Main {
    public static String message ;
    public String messageAfficher ="";
    private final BufferedReader buff;

    public static void main(String[] args) throws IOException{
        Main console = new Main(args[0]);
        String laLigne;
        while((laLigne = console.readLine()) !=null) {
            System.out.println(laLigne);
        }
        console.close();
    }

    public Main(String filePath) throws FileNotFoundException {
        InputStream flux=new FileInputStream(filePath);
        InputStreamReader lecture=new InputStreamReader(flux);
        buff=new BufferedReader(lecture);
    }

    public String readLine() throws IOException {
        return buff.readLine();
    }

    public void close() throws IOException {
        buff.close();
    }

}






