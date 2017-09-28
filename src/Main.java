public class Main {

    public static void main(String[] args) {
      menu();
    }

    static void menu () {
        System.out.println("Command Line Todo application");
        System.out.println("=============================");
        System.out.println("");
        System.out.println("Command line arguments:");
        System.out.println("-l   Lists all the tasks");
        System.out.println("-a   Adds a new task");
        System.out.println("-r   Removes an task");
        System.out.println("-c   Completes an task");
    }
}
