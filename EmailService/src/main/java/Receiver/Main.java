package Receiver;

import javax.mail.Message;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        EmailReceiver receiver = new EmailReceiver();
        Scanner scanner = new Scanner( System. in);
        System.out.println("Enter you username : ");
        String username = scanner. nextLine();
        System.out.println("Enter app password : ");
        String password = scanner. nextLine();
        List<Message> messages = receiver.getLetters(username,password,30);
    }
}
