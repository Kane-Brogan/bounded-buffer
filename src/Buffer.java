package kanebrogan.boundedbuffer.src;

/*
 * Class that represents the structure of the
 * bounded-buffer.
 * 
 * @author Kane Brogan 
 */
public class Buffer {
    private int[] buffer;
    private int in, out, counter, bufferSize, 
            nProducerThreads, nConsumerThreads;

    /* Buffer size, number of Consumer and Producer threads are passed through
    * the command line and initialised in Main.java
    */
    public Buffer(int bufferSize, int nProducerThreads, int nConsumerThreads) {
        buffer = new int[bufferSize];
        in = 0;
        out = 0;
        counter = 0;
        this.bufferSize = bufferSize;
        this.nProducerThreads = nProducerThreads;
        this.nConsumerThreads = nConsumerThreads;
    }
	
    public synchronized void insertItem(int item) {		
        try {
            // waits for a free space in the buffer if it is full
            while(counter == bufferSize) {
                wait();
            }

            // Adds item to the buffer
            buffer[in] = item;
            in = (in + 1) % bufferSize;
            counter++;	
            notifyAll();
        } 
        catch(InterruptedException e) {
                e.printStackTrace();
        }
    }
	
    public synchronized int removeItem() {  
        try {	
            while(counter < bufferSize) {
                wait();
            }      

            // Removes item from the buffer
            int item = buffer[out];
            out = (out + 1) % bufferSize;
            counter--;
            notifyAll();
            return item;
        } 
        catch(InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }
	
    // Getters & Setters
    
    public int[] getBuffer() {
        return buffer;
    }

    public void setBuffer(int[] buffer) {
        this.buffer = buffer;
    }
	
    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getnConsumerThreads() {
        return nConsumerThreads;
    }

    public void setnConsumerThreads(int nConsumerThreads) {
        this.nConsumerThreads = nConsumerThreads;
    }

    public int getnProducerThreads() {
        return nProducerThreads;
    }

    public void setnProducerThreads(int nProducerThreads) {
        this.nProducerThreads = nProducerThreads;
    }
}