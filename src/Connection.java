import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection implements Runnable
{


    static Socket client;
    static InputStream in;
    static OutputStream out;
    static byte[] buffer = new byte[1000];


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
                setup(new Socket(MainClass.ipAddress, MainClass.port2), false);
            }catch (Exception e)
            {
                System.out.println("Timout");
            }
        }
    }

    public static void setup(Socket client, boolean server) throws Exception
    {
        if (!server)
        {
            MainClass.seek.interrupt();
        }
        if (MainClass.connected != true)
        {
            MainClass.connection.client = client;
            in = client.getInputStream();
            out = client.getOutputStream();
            System.out.println("now connected");
            MainClass.begin();

            while (MainClass.connected && in.read(buffer) != -1)
            {
                readMessage(buffer);
                buffer = new byte[1000];
            }
            System.out.println("quit");
        }
    }

    private static void readMessage(byte[] message)
    {
        System.out.println(new String(message));
    }

    public static void sendMessage(String message) throws Exception
    {
        System.out.println("jsalkdjl");
        buffer = message.getBytes();
        out.write(buffer);
        buffer = new byte[1000];
    }

}
