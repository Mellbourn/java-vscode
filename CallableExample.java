import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> task = () -> {
            System.out.println("Performing a task in " + Thread.currentThread().getName());
            return 123; // Return some result
        };

        Future<Integer> future = executor.submit(task);

        // This will block until the task is completed and the result is available
        Integer result = future.get();
        System.out.println("Result of the task: " + result);

        executor.shutdown();
    }
}
