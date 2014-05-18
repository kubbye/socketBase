import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class TcpClient {
    public static void run1(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 8888);
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        System.out.println(br.readLine());
        s.close();
    }
    public static void run2(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 8888);
        OutputStream os = null;
        os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.println(new String("不许乱码".getBytes(),"iso-8859-1"));
        pw.flush();
        s.close();
    }
    
    public static void run3(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 8888);
        OutputStream os = null;
        os = s.getOutputStream();
        os.write("中国\r\n".getBytes("gbk"));

        //PrintWriter pw = new PrintWriter(os);
        //pw.println(new String("aaaaa".getBytes()));
        //pw.flush();
        
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is,"gbk");
        BufferedReader br = new BufferedReader(isr);
        String str=br.readLine();
        System.out.println(str);
        s.close();
    }
    
    public static void main(String[] args) throws Exception {
        run3(null);
    }
} 
