import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class NotifSender extends Thread {

    private ArrayList<Pack> List;

    public NotifSender(ArrayList<Pack> List) {
        this.List = List;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (List) {
                if (!List.isEmpty()) {

                    for (int i = 0; i < List.size(); i++) {
                        Pack P = List.get(i);
                        if (P.time <= 0) {
                            P.output.println(P.message);
                            List.remove(i);
                        } else {
                            P.time -= 50;
                        }
                    }
                }
            }
            try {
                sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Interruption occured");
            }

        }
    }
}
