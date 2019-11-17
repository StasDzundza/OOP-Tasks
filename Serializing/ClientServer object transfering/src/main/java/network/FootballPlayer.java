package network;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FootballPlayer implements Serializable {
    private String name = "Undefined";
    private String team = "Undefined";
    private String nationality = "Undefined";
    private int age = 0;
    private double salary = 0;

    public FootballPlayer(String name,String team,String nationality, int age, double salary){
        this.name = name;
        this.team = team;
        this.nationality = nationality;
        this.age = age;
        this.salary = salary;
    }

    public void writeInFile(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        writer.println(String.format("Player name : %s", name));
        writer.println(String.format("Player team : %s", team));
        writer.println(String.format("Player nationality : %s", nationality));
        writer.println(String.format("Player age : %d", age));
        writer.println(String.format("Player salary : %f", salary));
        writer.close();
    }
}
