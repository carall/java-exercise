package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.io.*;

public class ChatClient {

    private JTextArea inputArea = new JTextArea(4, 20);
    private JTextArea dialogArea = new JTextArea(20, 20);

    private DataOutputStream dos = null;
    private DataInputStream dis = null;

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        chatClient.buildGUI();
        chatClient.connectToServer();
        chatClient.buildAcceptThread();
    }


    private void buildGUI() {
        //set frame size and location
        JFrame mainFrame = new JFrame();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int defaultWidth = screenWidth / 3;
        int defaultHight = screenHeight / 2;
        mainFrame.setSize(defaultWidth, defaultHight);
        int cornerWidth = screenWidth / 3;
        int cornerHeight = screenHeight / 4;
        mainFrame.setLocation(cornerWidth, cornerHeight);

        //add dialog text area
        JPanel dialogPanel = new JPanel();
        dialogArea.setLineWrap(true);
        JScrollPane dialogScroll = new JScrollPane(dialogArea);
        dialogPanel.add(dialogScroll);
        mainFrame.add(BorderLayout.NORTH, dialogPanel);

        //add input text area
        JPanel inputPanel = new JPanel();
        inputArea.setLineWrap(true);
        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputPanel.add(inputScroll);
        mainFrame.add(BorderLayout.CENTER, inputPanel);

        //add button panel
        SendAction sendAction = new SendAction("发送");
        JButton sendButton = new JButton(sendAction);
        CloseAction closeAction = new CloseAction("关闭");
        JButton closeButton = new JButton(closeAction);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        buttonPanel.add(sendButton);
        mainFrame.add(BorderLayout.SOUTH, buttonPanel);

        //set button keystroke
        InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap amap = buttonPanel.getActionMap();
        imap.put(KeyStroke.getKeyStroke("shift ENTER"), "send");
        imap.put(KeyStroke.getKeyStroke("ctrl W"), "close");
        amap.put("send", sendAction);
        amap.put("close", closeAction);

        mainFrame.setTitle("chat client @ wys");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

    }

    private void connectToServer() {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 8888);
            dos = new DataOutputStream(clientSocket.getOutputStream());
            dis = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildAcceptThread() {
        new Thread(new AcceptThread()).start();
    }

    private class SendAction extends AbstractAction {
        private SendAction(String s) {
            putValue(Action.NAME, s);
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String sendString = inputArea.getText();
            try {
                dos.writeUTF(sendString);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialogArea.append("\n" + sendString);
            inputArea.setText("");
        }
    }

    private class CloseAction extends AbstractAction {
        private CloseAction(String s) {
            putValue(Action.NAME, s);
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                dos.close();
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }

    private class AcceptThread implements Runnable {
        private String acceptString = "";
        @Override
        public void run() {
            while (true) {
                try {
                    acceptString = dis.readUTF();
                    dialogArea.append("\n\t" + acceptString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
