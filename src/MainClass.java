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
    static boolean connected = false;
    static Thread listen = new Thread(connection), seek = new Thread();

    public static void main (String[] args)
    {
        setup();
    }

    private static void setup()
    {
        win.setVisible(true);
        win.setSize(500, 500);
        win.setLocationRelativeTo(null);
        win.add(pan);
        pan.add(but);
        but.addActionListener(new SendAction());
        listen.start();

    }

    public static void begin()
    {

    }

    static class SendAction implements ActionListener
    {

        public void actionPerformed(ActionEvent actionEvent)
        {

        }
    }

}
