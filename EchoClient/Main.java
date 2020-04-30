import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader echoes = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String message;
            String time;
            String response;

            do {
                System.out.println("Enter notification: ");
                message = scanner.nextLine();
                //if(message.isEmpty())
                   // throw new InputException();
                stringToEcho.println(message);


                if(!message.equals("exit")) {
                    System.out.println("Enter number od sec to wait: ");
                    time = scanner.nextLine();
                    stringToEcho.println(time);
                    System.out.println("Wainting for notification from server: ");
                    response = echoes.readLine();
                    System.out.println(response);
                }
            } while(!message.equals("exit"));

        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());

        }
    }
}
