package creational.builder;

import javax.xml.crypto.Data;

public class Database {
    private String name;
    private String username;
    private String password;
    private Integer port;
    private DatabaseType dbType;
    private Boolean isCompressed;

    private Database(){}

    public static Builder builder(){
        return new Builder();
    }
    // Step 1: Static Inner Class

    public static class Builder{
        // Step 2: Copy all the fields from outer class using duplication (to avoid forgetting copying fields or number of fields is very high)
        private Database db;

        public Builder(){
            this.db = new Database();
        }
        // Step 3: Create setters for Builder

        // Fluent interfaces
        public Builder setName(String name) {
            db.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            db.password = password;
            return this;
        }

        public Builder withCredentials(String username, String password) {
            db.username = password;
            db.password = password;
            return this;
        }

        public Builder setPort(Integer port) {
            db.port = port;
            return this;
        }

        public Builder setDbType(DatabaseType dbType) {
            db.dbType = dbType;
            return this;
        }

        public Builder mysql() {
            db.dbType = DatabaseType.MY_SQL;
            return this;
        }

        public Builder compressed() {
            db.isCompressed = true;
            return this;
        }

        public boolean validate(){
            return db.dbType != DatabaseType.MY_SQL || db.port == 3306;
        }

        public Database build(){
            boolean isValid = validate();

            if(!isValid)
                throw new IllegalArgumentException("Invalid Input");

            Database newDb = new Database();
            newDb.name = db.name;
            newDb.username = db.username;
            newDb.password = db.password;
            newDb.port = db.port;
            newDb.dbType = db.dbType;
            newDb.isCompressed = db.isCompressed;

            // clean duplicate db
            db = null;

            return newDb;
        }
    }

}
