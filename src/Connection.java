import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection implements Runnable
{


    static Socket client;
    static InputStream in;
    static OutputStream out;
    static int size = 500;
    static byte[] buffer = new byte[size], duffer = new byte[size];
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
                setup(new Socket(MainClass.ipAddress, MainClass.clientPort), false);
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

            while (MainClass.connected)
            {
                buffer = new byte[size];
                try
                {
                    in.read(buffer);
                }catch (Exception e)
                {
                    break;
                }
                String tempMessage = new String(buffer);
                System.out.println("2..han skicakr: " + tempMessage);
                readMessage(buffer);
            }
            System.out.println("quit");
            MainClass.connected = false;
            MainClass.chatlog.append("He left\n");
            MainClass.but.setText("Search");
        }
    }

    private static void readMessage(byte[] message)
    {
        String tempMessage = new String(message);
        translatedMessage = tempMessage.substring(tempMessage.indexOf(':') + 1, tempMessage.indexOf(';'));
        MainClass.chatlog.append("Him: " + translatedMessage + "\n");
        System.out.println(translatedMessage);

    }

    public static void sendMessage(String message) throws Exception
    {
        String translated = ":" + message + ";";
        duffer = translated.getBytes();
        out.write(duffer);
        out.flush();
        duffer = new byte[size];
    }

}
