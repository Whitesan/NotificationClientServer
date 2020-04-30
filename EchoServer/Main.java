
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Pack> List = new ArrayList<Pack>();
        NotifSender N = new NotifSender(List);
        N.start();
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket socket = serverSocket.accept();

                Echoer echoer = new Echoer(socket, List);
                echoer.start();

            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
