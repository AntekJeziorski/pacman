package threads;

public class Test1 implements Runnable
{
    @Override
    public void run()
    {
        System.err.println("test 1 started");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("test 1 stopped");
    }
}
