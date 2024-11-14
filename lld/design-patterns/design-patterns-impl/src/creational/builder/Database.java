package creational.builder;

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
        // Step 2: Copy all the fields from outer class
        private String name;
        private String username;
        private String password;
        private Integer port;
        private DatabaseType dbType;
        private Boolean isCompressed;

        // Step 3: Create setters for Builder

        // Fluent interfaces
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withCredentials(String username, String password) {
            this.username = password;
            this.password = password;
            return this;
        }

        public Builder setPort(Integer port) {
            this.port = port;
            return this;
        }

        public Builder setDbType(DatabaseType dbType) {
            this.dbType = dbType;
            return this;
        }

        public Builder mysql() {
            this.dbType = DatabaseType.MY_SQL;
            return this;
        }

        public Builder compressed() {
            isCompressed = true;
            return this;
        }

        public boolean validate(){
            if(dbType == DatabaseType.MY_SQL && port != 3306){
                return false;
            }

            return true;
        }

        public Database build(){
            boolean isValid = validate();

            if(!isValid)
                throw new IllegalArgumentException("Invalid Input");

            Database db = new Database();
            db.name = this.name;
            db.username = this.username;
            db.password = this.password;
            db.port = this.port;
            db.dbType = this.dbType;
            db.isCompressed = this.isCompressed;
            return db;
        }
    }

}
