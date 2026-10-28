import java.util.Scanner;

public class Main {
    private static final String SHELL_PROMPT = "$ ";
    public static void main(String[] args) throws Exception {
        boolean running = true;
        while (running) {
            System.out.print(SHELL_PROMPT);


            Scanner scanner = new Scanner(System.in);

            String command = scanner.nextLine();
            String[] splitCommand = command.split(" ", 2);
            if (command.equals("exit")) running = false;
            if (splitCommand[0].equals("echo")) { System.out.println(splitCommand[1]); }
            else System.out.printf("%s: command not found%n", command);
        }
    }
}
