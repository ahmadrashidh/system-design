import java.util.concurrent.locks.Lock;

public class Subtractor implements Runnable{

    private Count count;
    private Lock lock;

    Subtractor(Count count, Lock lock){
        this.count = count;
        this.lock = lock;
    }

    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            synchronized {
                // acquire the lock
                int value = this.count.getValue();
                value = value - i;
                this.count.setValue(value);
                lock.unlock();
                // release the lock
            }
        }
    }
}
