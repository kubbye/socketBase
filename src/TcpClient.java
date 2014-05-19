import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class TcpClient {
	private static final String ip="127.0.0.1";
	private static final int port=10221;
	
    public static void run1(String[] args) throws Exception {
        Socket s = new Socket(ip, port);
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        System.out.println(br.readLine());
        s.close();
    }
    /**
     * @param args
     * @throws Exception
     */
    public static void run2(String[] args) throws Exception {
        Socket s = new Socket(ip, port);
        OutputStream os = null;
        os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.println(new String("不许乱码".getBytes("utf-8"),"utf-8"));
        pw.flush();
        s.close();
    }
    
    public static void run3(String[] args) throws Exception {
        Socket s = new Socket(ip, port);
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
        //run2(null);
    	int i=1;
    	while(true && i++<5){
	    	TcpClient client=new TcpClient();
	    	ClientThread ct=client.new ClientThread();
	    	ct.start();
    	}
    }
    
    class ClientThread extends Thread{

		@Override
		public void run() {
			try {
				Socket s = new Socket(ip, port);
				OutputStream os = null;
				os = s.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				pw.println(new String("不许乱码".getBytes("utf-8"),"utf-8"));
				pw.flush();
				s.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
    }
} 
