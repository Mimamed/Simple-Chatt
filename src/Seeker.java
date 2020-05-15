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
            server = new ServerSocket(MainClass.noPort);
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
                MainClass.connection.setup(server.accept());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
