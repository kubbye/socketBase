import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TcpServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(10221);
        while (true) {
            Socket s = ss.accept();
            Thread t = new RequestProcesser(s);
            t.start();
        }
    }
}

class RequestProcesser extends Thread {
    private Socket socket;
    private String[] strs = { "程序写得好，要饭要到老！", "轻轻的我走了，正如我轻轻的来！", "大家好才真的好！",
            "你是谁？我是谁！你是我是谁？我是我是谁！", "春风吹，战鼓雷，当今社会谁怕谁？" };

    public RequestProcesser(Socket socket) {
        this.socket = socket;
    }

    /*public void run() {
        OutputStream os = null;
        try {
            os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            Random r = new Random();
            pw.println(strs[r.nextInt(strs.length)]);
            pw.flush();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(os!=null)try{os.close();}catch(Exception e){}
        }
    }*/
    
    public void run() {
        InputStream is= null;
        try {
            is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(is!=null)try{is.close();}catch(Exception e){}
        }
    }
}
