import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        // Create an ExecutorService with a fixed thread pool of size 3
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit Runnable tasks to the executor
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Executing task " + taskId + " on thread " + Thread.currentThread().getName());
            });
        }

        // Initiates an orderly shutdown in which previously submitted tasks are
        // executed, but no new tasks will be accepted
        executor.shutdown();
    }
}
