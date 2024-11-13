
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Runner{
    public static void main(String[] args){
        Count count = new Count();

        Adder adder = new Adder(count);
        Subtractor subtractor = new Subtractor(count);

        ExecutorService exeService = Executors.newCachedThreadPool();
        exeService.execute(adder);
        exeService.execute(subtractor);

        exeService.shutdown();

        try {
            exeService.awaitTermination(100, TimeUnit.SECONDS);
        } catch(Exception e) {
            System.out.println("Something wrong happens");
        }

        System.out.println(count.getValue());

    }
}