public class SharedVariable {
    // hared variable
    private static String sharedData = "";
    private static final Object lock = new Object(); // Mutex equivalent

    public static void main(String[] args) {
        //Writer thread
        Thread writerThread = new Thread(() -> {
            synchronized (lock) {
                sharedData = "Hello from Writer Thread!";
                System.out.println("Writer Thread: Data written to shared variable.");
            }
        });

        //Reader thread
        Thread readerThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Reader Thread: Reading shared variable: " + sharedData);
            }
        });

        //Start threads
        writerThread.start();
        try {
            writerThread.join(); // make sure writer thread finishes first
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Writer thread interrupted!");
        }

        readerThread.start();
        try {
            readerThread.join(); // wait for reader thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Reader thread interrupted!");
        }
    }
}
