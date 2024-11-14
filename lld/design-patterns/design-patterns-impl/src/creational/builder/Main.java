package creational.builder;

public class Main {
    public static void main(String[] args){
        Database db = Database.builder()
                .setName("Ahmad")
                .withCredentials("ahmaad","1998")
                .mysql()
                .compressed()
                .setPort(8000)
                .build();
    }
}
