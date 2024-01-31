import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    public static void main(String[] args) {
        // Create an ExecutorService with a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Simulate fetching data from three different sources
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> fetchData("Source1"), executor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> fetchData("Source2"), executor);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> fetchData("Source3"), executor);

        // Process each piece of data as it becomes available and then combine the
        // results
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3)
                .thenRun(() -> {
                    try {
                        String result1 = future1.get(); // These get calls will not block
                        String result2 = future2.get();
                        String result3 = future3.get();

                        String summary = processResults(result1, result2, result3);
                        System.out.println("Combined Result: " + summary);
                    } catch (Exception e) {
                        System.err.println("Error combining results: " + e.getMessage());
                    }
                });

        // Ensure all tasks are completed by blocking on the combined future
        combinedFuture.join();

        // Shutdown the executor
        executor.shutdown();
    }

    private static String fetchData(String source) {
        // Simulate time taken to fetch data
        try {
            Thread.sleep((long) (Math.random() * 2000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Data from " + source;
    }

    private static String processResults(String... results) {
        // Simulate processing and combining the results
        String combined = String.join(" + ", results);
        return "Processed [" + combined + "]";
    }
}
