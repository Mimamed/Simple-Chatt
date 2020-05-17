import java.net.ServerSocket;
import java.net.Socket;

public class Seeker implements Runnable
{

    static ServerSocket server;
    static Socket tempClient;

    public void run()
    {
        setup();
    }

    private static void setup()
    {
        try
        {
            server = new ServerSocket(MainClass.yourPort);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while (!MainClass.connected)
        {
            try
            {
                System.out.println("server");
                System.out.println(MainClass.seek.getState());
                tempClient = server.accept();
                MainClass.listen.interrupt();
                MainClass.connection.setup(tempClient, true);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
