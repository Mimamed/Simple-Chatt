import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection implements Runnable
{


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
                setup(new Socket(MainClass.ipAddress, MainClass.port2));
                MainClass.seek.interrupt();
            }catch (Exception e)
            {
                System.out.println("Timout");
            }
        }
    }

    public static void setup(Socket client) throws Exception
    {
        MainClass.connection.client = client;
        in = client.getInputStream();
        out = client.getOutputStream();
        System.out.println("now connected");
        MainClass.begin();
    }

    public static void sendMessage(String message) throws Exception
    {
        System.out.println("jsalkdjl");
    }

}
