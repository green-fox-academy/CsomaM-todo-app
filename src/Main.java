import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
      menu();
      System.out.println("\n" + "Give me an argument" + "\n");
      input();
    }

    static void menu () {
        System.out.println("Command Line Todo application");
        System.out.println("=============================" + "\n");
        System.out.println("Command line arguments:");
        System.out.println("-l   Lists all the tasks");
        System.out.println("-a   Adds a new task");
        System.out.println("-r   Removes an task");
        System.out.println("-c   Completes an task");
    }

    static void tasksOut () {

        try (BufferedReader br = new BufferedReader(new FileReader("Tasks.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }catch (IOException e) {
            System.out.println("Can't find Tasks.txt");

        }
    }

    static void input () {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equals("-l")) {
            tasksOut();
        } else if (input.equals("-a")) {

        } else if (input.equals("-r")) {

        }else if (input.equals("-c")) {

        } else {
            System.out.println("Please give me a valid argument");
        }
    }
}
