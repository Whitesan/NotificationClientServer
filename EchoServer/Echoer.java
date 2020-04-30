import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.ArrayBlockingQueue;

public class Echoer extends Thread {
    private Socket socket;
    private ArrayList<Pack> List;

    public Echoer(Socket socket, ArrayList<Pack> List) {
        this.socket = socket;
        this.List = List;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("New client");
            while (true) {
                String echoString = input.readLine();
                System.out.println("notification");
                String time = input.readLine();
                System.out.println("time");

                if (echoString.equals("exit")) {
                    break;
                }

                int timeInt = Integer.parseInt(time);
                Pack P = new Pack();
                P.output = output;
                P.message = echoString;
                P.time = timeInt;
                synchronized (List) {
                    List.add(P);

                }

            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
