import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection implements Runnable
{


    static String ipAddress = "95.109.71.23";
    static int port = 7779;
    static Socket client;
    static InputStream in;
    static OutputStream out;


    public void run()
    {
        StartUpConnection();
    }

    private void StartUpConnection()
    {
        try
        {
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while (client == null)
        {
            try
            {
                System.out.println("retrying");
                client = new Socket(ipAddress, port);
                System.out.println(MainClass.seek.getState());
                MainClass.seek.interrupt();
                System.out.println(MainClass.seek.getState());
                in = client.getInputStream();
                out = client.getOutputStream();
                System.out.println("now connected");
            }catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("Timout");
            }
        }
    }

    public static void sendMessage(String message) throws Exception
    {
        System.out.println("jsalkdjl");
    }

}
