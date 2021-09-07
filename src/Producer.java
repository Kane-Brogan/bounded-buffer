package kanebrogan.boundedbuffer.src;

/*
 * Thread class to add items to the buffer.
 * 
 * @author Kane Brogan
 */

public class Producer extends Thread {
    private long threadID;
    private final Buffer buffer;
    private int item;

    public Producer(String s, Buffer b) {
        super(s);
        this.buffer = b;
    }

    @Override
    public void run() {
        setThreadID(this.getId());

        while(!Thread.currentThread().isInterrupted()) {	
            //creates an item ands add it to the buffer
            this.item = (int) (Math.random() * Constants.MAXRAND);
            this.buffer.insertItem(this.item);
            System.out.println("Producer " + this.threadID 
                    + " successfully entered " + this.item 
                    + "\nThe buffer now contains " 
                    + buffer.getCounter() + " items");

            // interrupts the producer if it is full
            if(buffer.getCounter() == buffer.getBufferSize()) {
                Thread.currentThread().interrupt();
            }

            try {
                // simulates a delay
                Producer.sleep((long) (Math.random() * Constants.MAXSLEEP));
            } 
            catch (InterruptedException e1) {
                // prints message if producer is interrupted
                System.out.println("Interrupting producer(s)...");
            }
        }
    }

    // Getters & Setters
    
    public Buffer getBuffer() {   
        return buffer;
    }

    public long getThreadID() {
        return threadID;
    }

    public void setThreadID(long threadID) {
        this.threadID = threadID;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
}