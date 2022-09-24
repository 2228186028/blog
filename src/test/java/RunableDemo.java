import org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks;

public class RunableDemo implements Runnable{

    private  Thread t;
    private  String threadName;

    RunableDemo(String name){
        threadName = name;
        System.out.println("Have achieved thread ...");
    }

    @Override
    public void run() {
        System.out.println("Running" + threadName);

        try{
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " exiting.");
        }
        System.out.println("Thread " + threadName + "exiting. ");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public static void main(String args[]){
        RunableDemo R1 = new RunableDemo( "Thread-1");
        R1.start();

        RunableDemo R2 = new RunableDemo("Thread-2");
        R2.start();
    }

}
