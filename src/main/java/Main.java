import shell.Utils;
import shell.commands.Command;

import java.util.Scanner;

public class Main {
    private static final String SHELL_PROMPT = "$ ";

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.print(SHELL_PROMPT);

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();
            String[] splitInput = input.split(" ", 2);
            try {
                Class<? extends Command> commandClass = Class.forName("shell.commands." + Utils.capitalize(splitInput[0])).asSubclass(Command.class);
                Command command = commandClass.getDeclaredConstructor().newInstance();
                command.execute(splitInput[1]);
            } catch (ClassNotFoundException e) {
                System.out.printf("%s: command not found%n", splitInput[0]);
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
    }
}
