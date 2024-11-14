package singleton;

import java.sql.Connection;

public class ConnectionPool {

    // Step.3: Local Attribute
    private static ConnectionPool INSTANCE;

    // Step.1: Constructor Hiding
    private ConnectionPool(){
    }

    // Step.2: Static Initialiser
    public static ConnectionPool getInstance(){

        // Step.4: Initialisation Logic

        // Making the critical section to be double-checked locking to be thread-safe.
        if(INSTANCE == null){
            synchronized(ConnectionPool.class){
                if(INSTANCE == null)
                    INSTANCE = new ConnectionPool();
            }
        }

        return INSTANCE;
    }

}
