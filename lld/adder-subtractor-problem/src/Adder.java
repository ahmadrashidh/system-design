import java.util.concurrent.locks.Lock;

public class Adder implements Runnable {

    private Count count;
    private Lock lock;

    public Adder(Count count, Lock lock){
        this.count = count;
        this.lock = lock;
    }

    public void run(){
        for(int i = 0; i < 100; i++){
            lock.lock();
            int value = this.count.getValue();
            value = value + i;
            this.count.setValue(value);
            lock.unlock();
        }
    }




}
