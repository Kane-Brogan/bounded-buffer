package kanebrogan.boundedbuffer.src;

/*
 * Main function that initialises the buffer and
 * its associated threads. This is the class that
 * is executed from the command line.
 * 
 * @author Kane Brogan 
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Launching program...");

        //initialising buffer (parses integers through the command line)
        //Buffer buffer = new Buffer(Integer.parseInt(args[0]), 
        //        Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        Buffer buffer = new Buffer(5, 1, 1);
        
        // create requested number of producer threads
        for(int i = 1; i <= buffer.getnProducerThreads(); i++) {
            Producer tempP = new Producer("Producer" + i, buffer);
            tempP.start();
            System.out.println(tempP.getName() + " ID: "
                    + tempP.getId() + " started...");
        }

        // create requested number of consumer threads
        for(int i = 1; i <= buffer.getnConsumerThreads(); i++) {
            Consumer tempC = new Consumer("Consumer" + i, buffer);
            tempC.start();
            System.out.println(tempC.getName() + " ID: "
                    + tempC.getId() + " started...");
        }

        // sleeps/runs for 10 seconds
        try {
            Thread.sleep(Constants.SLEEPTIME);
            System.out.println("Sleeping...");
        } 
        catch (InterruptedException e) {
            System.out.println("Error: could not sleep for 10 seconds!");
        }

        // exit program
        System.out.println("Terminating program...");
        Runtime.getRuntime().halt(0);
    }
}