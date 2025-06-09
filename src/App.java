import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!"+LocalTime.now());
        LocalTime reminderTime = LocalTime.now().plusMinutes(1);// LocalTime.parse();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        long delay = Duration.between(LocalTime.now(), reminderTime).getSeconds();
        scheduler.schedule(() -> {
            System.out.println("🔔 Reminder: "+LocalTime.now());
        }, delay, TimeUnit.SECONDS);
    }
}
