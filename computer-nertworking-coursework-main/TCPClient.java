package TCPSocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;



public class TCPClient {
    private Socket tcpSocket;
    private InetAddress serverAddress;
    private int serverPort;
    private Scanner scanner;


    private TCPClient(InetAddress serverAddress, int serverPort) throws Exception {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;

        //initiating the connection with server
        //creating stream socket

        this.tcpSocket = new Socket(this.serverAddress, this.serverPort);
        this.scanner = new Scanner(System.in);
    }


    private void start() throws IOException {
        String input;

        //making new PrintWriter from tcpSocket
        //putting code in a loop, so it keeps sending datagrams

        while (true) {
            input = scanner.nextLine();
            PrintWriter output = new PrintWriter(this.tcpSocket.getOutputStream(), true);
            output.println(input);
            output.flush();
        }
    }

    public static void main(String[] args) throws Exception {


        //setting server IP and port number to local address

        InetAddress serverIP = InetAddress.getByName("127.0.0.1"); // local IP address
        int port = 7077;

        if (args.length > 0) {
            serverIP = InetAddress.getByName(args[0]);
            port = Integer.parseInt(args[1]);
        }

        // calling constructor and passing IP and port

        TCPClient client = new TCPClient(serverIP, port);
        System.out.println("\r\n Connected to Server: " + client.tcpSocket.getInetAddress());
        client.start();
    }
}
