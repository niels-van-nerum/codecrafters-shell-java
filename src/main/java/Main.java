import commands.Command;

import java.lang.reflect.Constructor;
import java.util.Scanner;

public class Main {
    private static final String SHELL_PROMPT = "$ ";
    public static void main(String[] args) throws Exception {
        boolean running = true;
        while (running) {
            System.out.print(SHELL_PROMPT);

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();
            String[] splitInput = input.split(" ", 2);
            try {
                Class<? extends Command> commandClass = Class.forName(splitInput[0]).asSubclass(Command.class);
                Command command = commandClass.getDeclaredConstructor().newInstance();
                command.execute(splitInput[1]);
            } catch (ClassNotFoundException e) {
                System.out.printf("%s: command not found%n", splitInput[0]);
            }
        }
    }
}
