# Introduction to Processes & Threads

**Processor** - In computer, tasks are done by processor.
**Core** - Processor are divided into units knows as cores.
**Program** - Application sitting on a disk, ready to be run.
**Process** - Program in execution.
**Thread** - way to allow one process to do multiple task at the same time.

- At one time, computer can execute number of core tasks, depending on number of cores.
- An application does more than one process at time.
- One core can only execute one thread at a time.
- One thread can execute only one task at a time.
- Whenever process creates threads, it is then managed by CPU Scheduler

## Different types of concurrency

**Concurrency**
- Way to handle multiple task with each task at different stages of completion.
- May or may not making progress at the same time.
  
**Parallelism**
- Multiple tasks making progress at the same time
- sometimes, tasks are at multiple stages of completion.

If system is parallel, it is concurrent.
If system is concurrent, it may or may not be parallel

**Context Switching**

- Switching between tasks to do parallelism
- Overhead of remembering where task is left at and resetting to it.


## Process Control Block
> contains all information about/for execution of process

## Implementing Multithreading

Create threads for tasks you need to run independently.

### Using Thread class


- Create class for every task you want to run parallely
- Extend `Thread` class
- Implement `run` method with functionality.
- Call `start` method to execute thread

```java
class SquarePrinter extends Thread{
    private int num;

    SquarePrinter(int num){
        this.num = num;
    }

    public void run(){
        System.out.println(num*num);
    }
}

class Main{
    public static void main(String[] args){
        Thread squarePrinterThread = new SquarePrinter(12);
        squarePrinterThread.start();
    }
}
```

### Using Runnable

- Create class for every task you want to run parallely.
- Class implements `Runnable` interface.
- Override `run` method of the interface.

Write a program to print `"Hello World"` in different thread.

```java

public class HelloWorldPrinter implements Runnable {

    @Override
    public void run(){
        System.out.println("Hello World");
    }
}

public static void main(String[] args){

    HelloWorldPrinter helloWorldPrinter = new HelloWorldPrinter();

    Thread helloWorldPrinterThread = new Thread(helloWorldPrinter);
    helloWorldPrinterThread.start();

    // or helloWorldPrinter.run();
}

```


### Using Executor

- Create class for every task you want to run parallely.
- Class implements `Runnable` interface.
- Override `run` method of the interface.
- In main method, create `ExecutorService` to create thread.

**Thread Pool**
> Limited numbers of threads for a process where tasks are queued to be processed


Print numbers from 1 to 100, each from separate thread using Thread pool

```java
class NumberPrinter implements Runnable {

    int noToPrint;

    NumberPrinter(int noToPrint){
        this.noToPrint = noToPrint;
    }
    public void run(){
        System.out.println(this.noToPrint);
    }
}

public class Main {
    public static void main(String[] args){
        ExecutorService exeService = Executors.newFixedThreadPool(10);
        for(int i = 1; i <= 100; i++){
            NumberPrinter numPrinter = new NumberPrinter(i);
            exeService.execute(numPrinter);
        }
    }
}
```

**SingleThreadExecutor** - execute in single thread only
**FixedThreadPool** - execute in fixed number of threads by putting tasks in queue. For long running tasks.
**CachedThreadPool** - reuse threads (instead of killing) if its available, otherwise create one. For short running tasks.

### How to decide number of threads in Thread Pool?


- A Single Threaded Application may not end up utilising all my computer resources to full potential. 
- At max, only number of cores of threads can be done in parallel
- **Hyperthreading** - allows one core to run two threads at the same time. Therefore, number of threads can be twice of number of cores.
- Multithreading increases CPU utilization. 
- Although, if thread has been given a task, next will not run until current one is done.
- There are tasks that does not use CPU in execution - known as IO-intensive tasks. Others are CPU-intensive tasks.
- For CPU-intensive tasks, number of threads should be equal to twice the number of cores. For the other, it can be greater than that.


### Using callable
> allows to return from `run()` method

- in multithreading, task may be queued and so, return is not available instantly.
- There, we have `Future` - container for async computation.

1. Create task class implementing `Callable`.
2. Implement `call()` method.
3. User `ExecutorService` and invoke `submit()` method (non-blocking call - it won't wait for return)
4. The `submit()` method returns `Future`
5. Check and get returned value using `isDone()` and `get()` methods
6. Unlike `isDone()`, `get()` is blocking call, it stops `main` thread and completed the task and return the value.


```java
class SquarePrinter implements Callable<Integer>{
    private int num;

    SquarePrinter(int num){
        this.num = num;
    }

    @Override
    public Integer call(){
        return this.num * this.num;
    }
}

class Main{
    public static void main(String[] args){
        ExecutorService exeService = Executors.newCachedThreadPool();
        Future<Integer> futSqNum = exeService.submit(new SquarePrinter(12));

        if(futSqNum.isDone()){
            System.out.println(futSqNum.get());
        }
    }
}
```