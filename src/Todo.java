import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Todo {

    public static void main(String[] args) {
        if (args.length == 0) {
            menu();
        } else if (args[0].startsWith("-l")) {
            tasksOut();
        } else if (args[0].startsWith("-a")) {
            try {
                addTask(args[1]);
            } catch (IndexOutOfBoundsException i) {
                System.out.println("Unable to add: no task provided");
            }
        } else if (args[0].startsWith("-r")) {
            try {
                removeTask(Integer.parseInt(args[1]));
            } catch (IndexOutOfBoundsException i) {
                System.out.println("Unable to delete: no task provided");
            }
        }else if (args[0].startsWith("-c")) {
            try {
                completeTask(Integer.parseInt(args[1]));
            } catch (IndexOutOfBoundsException i) {
                System.out.println("Unable to delete: no task provided");
            }
        } else {
            System.out.println("Please give me a valid argument.");
        }
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
            int n = 0;
            String line = br.readLine();
            if (line == null || line == "") {
                System.out.println("No todos for today! :)");
            } else {
                while (line != null && line != "") {
                    if (line.contains("[x]")) {
                        System.out.println(n + 1 + "." + line);
                        n++;
                    } else {
                        System.out.println(n + 1 + "." + "[ ]  " + line);
                        n++;
                    }
                    line = br.readLine();
                }
            }
        }catch (IOException e) {
            System.out.println("Can't find Tasks.txt.");
        }
    }

    static String taskIndex (int index) {
        String lookingFor = "";
        try (BufferedReader br = new BufferedReader(new FileReader("Tasks.txt"))) {
            int n = 1;
            String line = br.readLine();
            while (line != null) {
                if (n == index) {
                    lookingFor = line;
                    n++;
                } else {
                    line = br.readLine();
                    n++;
                }

            }
            System.out.println(lookingFor);
        }catch (IOException e) {
            System.out.println("Can't find Tasks.txt.");
        }
        return lookingFor;
    }

    static void addTask (String task) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Tasks.txt", true))) {
            bw.newLine();
            bw.write(task);
        }catch (IOException e) {
            System.out.println("Can't find Tasks.txt.");
        }
    }

    static void removeTask (int index) {
        File file = new File("Tasks.txt");
        try {
            List<String> out = Files.readAllLines(file.toPath());
            out.remove(index + 1);
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Can't find Tasks.txt.");
        }
    }

    static void completeTask(int index) {
        String task = taskIndex(index);
        String completedTask = "[x]  " + task;
        removeTask(index);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Tasks.txt", true))) {
            bw.write(completedTask);
        }catch (IOException e) {
            System.out.println("Can't find Tasks.txt.");
        }
    }
}
