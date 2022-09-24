import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception{
        int i = 0;
        for(; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }

    public static void main(String[] args){
        CallableDemo ctt = new CallableDemo();
        FutureTask<Integer> ft = new FutureTask<>(ctt);

        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值" + i);
            if(i==0){
                new Thread(ft, "有返回值的线程");
            }
        }
        try {
            System.out.println("子线程的返回值" + ft.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
