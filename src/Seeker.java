import java.net.ServerSocket;

public class Seeker implements Runnable
{

    static ServerSocket server;

    public void run()
    {
        setup();
    }

    private static void setup()
    {
        try
        {
            server = new ServerSocket(7778);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        while (!MainClass.connected)
        {
            try
            {
                System.out.println("server");
                MainClass.connection.client = server.accept();
                MainClass.begin();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
