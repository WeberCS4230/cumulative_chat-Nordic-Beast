package cumulative.chat;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ChatWindow extends JFrame {

    //Creating everything necessary for the window
    private JPanel displayPanel = new JPanel();
    private JPanel inputPanel = new JPanel();

    JTextArea textDisplay = new JTextArea();
    private JTextArea textInput = new JTextArea();

    private JScrollPane displayScroll = new JScrollPane(textDisplay);
    private JScrollPane inputScroll = new JScrollPane(textInput);

    private JButton sendButton = new JButton("send");
    private String inputString = new String();

    public ChatWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);
        BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.setResizable(false);

        textDisplay.setEditable(false);
        textDisplay.setLineWrap(true);
        textDisplay.setWrapStyleWord(true);

        /*
        * Set max and min sized because otherwise it looked wonky
         */
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        displayPanel.setMaximumSize(new Dimension(500, 300));
        displayPanel.setMinimumSize(new Dimension(500, 300));
        displayPanel.add(displayScroll);
        displayPanel.setBorder(new EmptyBorder(10, 10, 40, 10));

        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
        inputPanel.setMaximumSize(new Dimension(500, 200));
        inputPanel.setMinimumSize(new Dimension(500, 200));
        inputPanel.add(inputScroll);
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        AbstractAction submit = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textInput.selectAll();
                inputString = textInput.getSelectedText();
                textDisplay.append("\n" + inputString + "\n");
                textInput.setText(null);
            }
        };
        sendButton.addActionListener(submit);
        sendButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK), "submitted");
        sendButton.getActionMap().put("submitted", submit);

        this.add(displayPanel, BorderLayout.NORTH);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(sendButton, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    public String getIP(){
        String inIP;
        inIP = JOptionPane.showInputDialog(this, "What is the IP of the Server?", null);
        return inIP;
    }
    
    public void connect(){
        Client client = new Client();
    }

    /*
    * From here on, it's client/connection stuff.
     */
    private class Client {
        public Client(){
            Socket socket;
            String IP = getIP();
        }
    }

}
