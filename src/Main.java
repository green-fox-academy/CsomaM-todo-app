import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        menu();
        do {
            System.out.println("\n" + "Give me an argument." + "\n");
            input = sc.nextLine();
            arguments(input);
        } while (!input.equals("exit"));

    }

    static void menu () {
        System.out.println("\n" + "Command Line Todo application");
        System.out.println("=============================" + "\n");
        System.out.println("Command line arguments:");
        System.out.println("-l   Lists all the tasks");
        System.out.println("-a   Adds a new task");
        System.out.println("-r   Removes an task");
        System.out.println("-c   Completes an task");
    }

    static void tasksOut () {
        try (BufferedReader br = new BufferedReader(new FileReader("Tasks.txt"))) {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }catch (IOException e) {
            System.out.println("Can't find Tasks.txt.");

        }
    }

    static void arguments (String input) {
        if (input.equals("-l")) {
            tasksOut();
        } else if (input.equals("-a")) {
            addTask();
        } else if (input.equals("-r")) {
            removeTask();
        }else if (input.equals("-c")) {
            completeTask();
        } else if (input.equals("exit")){

        } else {
            System.out.println("Please give me a valid argument.");
        }
    }

    static void addTask () {
        System.out.println("Give me a task.");
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Tasks.txt", true))) {
            bw.newLine();
            bw.write(task);
        }catch (IOException e) {
            System.out.println("Can't find Tasks.txt.");
        }
    }

    static void removeTask () {
        System.out.println("Which task do you want removed?");
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        File file = new File("Tasks.txt");
        try {
            List<String> out = Files.lines(file.toPath())
                    .filter(line -> !line.contains(task))
                    .collect(Collectors.toList());
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Can't find Tasks.txt.");
        }
    }

    static void removeTaskLite (String task) {
        File file = new File("Tasks.txt");
        try {
            List<String> out = Files.lines(file.toPath())
                    .filter(line -> !line.contains(task))
                    .collect(Collectors.toList());
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Can't find Tasks.txt.");
        }
    }

    static void makeItCheckable () {
        
    }

    static void completeTask() {
        System.out.println("Which task do you want completed?");
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        String completedTask = "[x]  " + task;
        removeTaskLite(task);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Tasks.txt", true))) {
            bw.write(completedTask);
        }catch (IOException e) {
            System.out.println("Can't find Tasks.txt.");
        }
    }

}
