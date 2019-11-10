package network;

import java.io.Serializable;

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

    public void showInformation(){
        System.out.println(String.format("Player name : %s", name));
        System.out.println(String.format("Player team : %s", team));
        System.out.println(String.format("Player nationality : %s", nationality));
        System.out.println(String.format("Player age : %d", age));
        System.out.println(String.format("Player salary : %f", salary));
    }
}
