import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class MainClass
{

    static String ipAddress = "81.227.57.155", ipaddress2 = "95.109.71.23";
    static int clientPort = 7777, yourPort = 7778, noPort = 7779;
    static JFrame win = new JFrame("Title"), win2 = new JFrame("Cridentials");
    static JPanel pan = new JPanel(), pan2 = new JPanel();
    static JButton but = new JButton("send"), but2 = new JButton("Submit");
    static JTextField text = new JTextField(""), textip = new JTextField(""), textpo = new JTextField(""), textpo2 = new JTextField("");
    static JTextArea chatlog = new JTextArea("");
    static JScrollPane scrollPane = new JScrollPane(chatlog);
    static Connection connection = new Connection();
    static Seeker seeker = new Seeker();
    static boolean connected = false;
    static Thread listen = new Thread(connection), seek = new Thread(seeker);

    public static void main (String[] args)
    {
        cridentials();
        //setup();
    }

    private static void cridentials()
    {
        win2.setVisible(true);
        win2.setSize(500, 500);
        win2.setLocationRelativeTo(null);
        win2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win2.setResizable(false);
        pan2.setLayout(null);
        win2.add(pan2);
        pan2.add(textip);
        pan2.add(textpo);
        pan2.add(but2);
        pan2.add(textpo2);

        but2.setBounds(225, 50, 50, 20);
        textip.setBounds(50, 80, 400, 20);
        textpo.setBounds(50,120,400,20);
        textpo2.setBounds(50,160,400,20);

        but2.setMargin(new Insets(0,0,0,0));
        but2.setFocusPainted(false);
        but2.addActionListener(new Submit());
        textip.setText("Write your ip address here...");
        textpo.setText("Write your server port number here...");
        textpo2.setText("Write your client port number here...");
    }

    private static void setup()
    {
        win.setVisible(true);
        win.setSize(500, 500);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setTitle("Discord is worse");
        win.setResizable(false);
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
        but.setFocusPainted(false);

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
                    but.setEnabled(false);
                    but.setText("Sänd");
                    chatlog.setText("");
                    connection.client.close();
                    seeker.server.close();
                    seek.interrupt();
                    listen.interrupt();
                    seeker = new Seeker();
                    connection = new Connection();
                    seek = new Thread(seeker);
                    listen = new Thread(connection);
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

    static class Submit implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            boolean loop = true;
            while (loop)
            {
                try
                {
                    ipAddress = textip.getText();
                    clientPort = Integer.parseInt(textpo2.getText());
                    yourPort = Integer.parseInt(textpo.getText());
                    loop = false;
                }catch (Exception u)
                {
                    loop = true;
                    textip.setText("Write it correctly");//har för lite tid att få fram vilken
                    textpo.setText("Write it correctly");
                    textpo2.setText("Write it correctly");
                }
            }
            win2.dispose();
            setup();
        }
    }
}
