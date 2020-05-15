import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class MainClass
{

    static JFrame win = new JFrame("Title");
    static JPanel pan = new JPanel();
    static JButton but = new JButton("send");
    static Connection connection = new Connection();
    static Seeker seeker = new Seeker();
    static boolean connected = false;
    static Thread listen = new Thread(connection), seek = new Thread(seeker);

    public static void main (String[] args)
    {
        setup();
    }

    private static void setup()
    {
        win.setVisible(true);
        win.setSize(500, 500);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.add(pan);
        pan.add(but);
        but.addActionListener(new SendAction());
        listen.start();
        seek.start();

    }

    public static void begin()
    {
        System.out.println("Connected");
        connected = true;
    }

    static class SendAction implements ActionListener
    {

        public void actionPerformed(ActionEvent actionEvent)
        {

        }
    }

}
