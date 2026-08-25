import java.util.Scanner;
public class Greeting {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = scanner.nextLine();
        System.out.print("What do you do for work? ");
        String role = scanner.nextLine();
        System.out.println("Hello, " + name + "! Welcome to the program. As a " + role + ", you are in exactly the right place.");
        scanner.close();
    }
}