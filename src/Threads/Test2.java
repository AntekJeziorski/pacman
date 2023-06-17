package Threads;

public class Test2 implements Runnable
{
    @Override
    public void run()
    {
        System.err.println("test 2 started");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("test 2 stopped");
    }
}
