import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection implements Runnable
{


    static Socket client;
    static InputStream in;
    static OutputStream out;
    static int size = 1024;
    static byte[] buffer = new byte[size];
    static String translatedMessage = "";


    public void run()
    {
        StartUpConnection();
    }

    private void StartUpConnection()
    {
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
                buffer = new byte[size];
            }
            System.out.println("quit");
        }
    }

    private static void readMessage(byte[] message)
    {
        String tempMessage = new String(message);
        System.out.println("han skicakr: " + tempMessage);
        translatedMessage = tempMessage.substring(tempMessage.indexOf(':') + 1, tempMessage.indexOf(';'));
        MainClass.chatlog.append("Him: " + translatedMessage + "\n");
        System.out.println(translatedMessage);

    }

    public static void sendMessage(String message) throws Exception
    {
        System.out.println("jsalkdjl");
        String translated = ":" + message + ";";
        System.out.println("Sent: " + translated);
        buffer = translated.getBytes();
        out.write(buffer);
        buffer = new byte[size];
    }

}
