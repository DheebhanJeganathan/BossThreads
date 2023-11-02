import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        int numThreads = 100;
        int numElements = 100000000;
        int elementsPerThread = numElements / numThreads;


        //Creates array w/ random #s 1-10
        int[] arr = new int[numElements];
        for (int i = 0; i < numElements; i++) {
            arr[i] = (int) (Math.random() * 10) + 1; // Initialize the array with integers from 0 to 99,999,999
        }

        AtomicLong sum = new AtomicLong(0);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        //Runs through all 100 threads
        for (int i = 0; i < numThreads; i++) {
            int start = i * elementsPerThread;
            int end = (i + 1 == numThreads) ? numElements : (i + 1) * elementsPerThread;

            executor.execute(() -> {
                long threadSum = 0;
                for (int j = start; j < end; j++) {
                    threadSum += arr[j];
                }
                sum.addAndGet(threadSum);
            });
        }

        executor.shutdown();


        //Prints sum
        System.out.println("Sum: " + sum.get());
    }
}
