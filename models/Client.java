package models;

public class Client {
    int number;
    int dette = 0;

    public Client() {
        this.number = (int) (Math.random() * 6 + 1);
    }
}
