import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class MainClass
{

    static String ipAddress = "95.109.71.23";
    static int port2 = 7778, port1 = 7777, noPort = 7779;
    static JFrame win = new JFrame("Title");
    static JPanel pan = new JPanel();
    static JButton but = new JButton("send");
    static JTextField text = new JTextField("");
    static JTextArea chatlog = new JTextArea("");
    static JScrollPane scrollPane = new JScrollPane(chatlog);
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
        pan.setLayout(null);
        win.add(pan);
        pan.add(but);
        pan.add(text);
        pan.add(scrollPane);
        but.setBounds(225, 50, 50, 20);
        text.setBounds(50, 80, 400, 20);
        scrollPane.setBounds(50,120,400,200);
        chatlog.setEditable(false);
        but.setMargin(new Insets(0,0,0,0));
        but.addActionListener(new SendAction());
        but.setEnabled(false);
        search();
    }

    public static void search()
    {
        seek.start();
        listen.start();
    }

    public static void begin()
    {
        System.out.println("Connected");
        connected = true;
        but.setEnabled(true);
        text.setEditable(true);
    }

    static class SendAction implements ActionListener
    {

        public void actionPerformed(ActionEvent actionEvent)
        {
            try
            {
                if (text.getText().indexOf(':') == -1 && text.getText().indexOf(';') == -1 && connected)
                {
                    chatlog.append("you: " + text.getText() + "\n");
                    text.setEditable(false);
                    connection.sendMessage(text.getText());
                    text.setText("");
                    text.setEditable(true);
                }
                else if (connected == false)
                {
                    chatlog.setText("");
                    seek.interrupt();
                    listen.interrupt();
                    search();
                }
                else
                {
                    text.setText("No \":\" or \";\" characters");
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
