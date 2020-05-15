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
            System.out.println(MainClass.seek.getState());
            server = new ServerSocket(7770);
            MainClass.connection.client = server.accept();
            MainClass.begin();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
