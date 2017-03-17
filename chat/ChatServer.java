package chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private static ServerSocket serverSocket;
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChatServer chatServer = new ChatServer();
        chatServer.startServer();
    }

    private void startServer() {
        while (true) {
            try {
                Socket tempSocket = serverSocket.accept();
                new Thread(new ChatServerThread(tempSocket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ChatServerThread implements Runnable {
        private Socket socket = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;

        ChatServerThread(Socket s) {
            socket = s;
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            try {
                while (true) {
                    String comingStr = dis.readUTF();
                    System.out.println(comingStr);
                    dos.writeUTF(comingStr + " from server!");
                }
            } catch (IOException e) {
//                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                    if (dis != null) {
                        dis.close();
                    }
                    if (dos != null) {
                        dos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}