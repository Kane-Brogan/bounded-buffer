package kanebrogan.boundedbuffer.src;

/*
 * Thread class to remove items from the buffer.
 * 
 * @author Kane Brogan 
 */
public class Consumer extends Thread {
    private long threadID;
    private final Buffer buffer;
    private int item;

    public Consumer(String s, Buffer b) {
        super(s);
        this.buffer = b;
    }

    @Override
    public void run() {
        setThreadID(this.getId());

        while(!Thread.currentThread().isInterrupted()) {
            // removes an item from the buffer
            item = buffer.removeItem();

            // error handling if consumer tries to remove an item from outside the array
            if(item == -1)
            {
                System.out.println("Error: could not consume Item from the buffer!");
                System.out.println("Consumer " + this.getId() + " exiting now");
                Thread.currentThread().interrupt();
            }

            // print status 
            System.out.println("Consumer " + this.item + " consumed"
                    + "\nThe buffer now contains " + buffer.getCounter() 
                    + " items");
            
            // interrupts consumer once an item has been removed from buffer
            Thread.currentThread().interrupt();
            try {
                // simulates a delay
                Consumer.sleep((int) (Math.random() * Constants.MAXRAND));
            }
            catch (InterruptedException e) {
                // prints message if producer is interrupted
                System.out.println("Interrupting consumer(s)...");
            }
        }
    }

    // Getters & Setters
    
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
