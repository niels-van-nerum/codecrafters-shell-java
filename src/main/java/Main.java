import shell.Utils;
import shell.commands.Command;

private static final String SHELL_PROMPT = "$ ";

void main() {
    while (true) {
        IO.print(SHELL_PROMPT);

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] splitInput = input.split(" ", 2);

        boolean foundValidCommand = tryBuiltinCommands(splitInput);
        if (!foundValidCommand) System.out.printf("%s: command not found%n", splitInput[0]);
    }
}

public static boolean tryBuiltinCommands(String[] splitInput) {
    try {
        Class<? extends Command> commandClass = Class.forName("shell.commands." + Utils.capitalize(splitInput[0])).asSubclass(Command.class);
        Command command = commandClass.getDeclaredConstructor().newInstance();
        command.execute(splitInput.length == 2 ? splitInput[1] : null);
        return true;
    } catch (Exception _) {
        return false;
    }
}
