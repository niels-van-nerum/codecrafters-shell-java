package shell.commands;

import shell.Utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

public class Type implements Command {

    @Override
    public void execute(String args) {
        boolean foundCommand = tryBuiltinCommands(args);
        if (!foundCommand) {
            foundCommand = tryPathCommands(args);
        }
        if (!foundCommand) System.out.printf("%s: not found%n", args);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.builtin;
    }

    public boolean tryBuiltinCommands(String args) {
        try {
            Class<? extends Command> commandClass = Class.forName("shell.commands." + Utils.capitalize(args)).asSubclass(Command.class);
            Command command = commandClass.getDeclaredConstructor().newInstance();
            CommandType type = command.getCommandType();
            System.out.printf("%s is a shell %s%n", args, type.toString().toLowerCase());
            return true;
        } catch (Exception _) {
            return false;
        }
    }

    public boolean tryPathCommands(String args) {
        String path = System.getenv("PATH");
        IO.println(path);
        String[] paths = path.split(File.pathSeparator);

        Optional<String> commandPath = Arrays.stream(paths).toList()
                .stream()
                .peek(IO::println)
                .filter(p -> Files.isExecutable(Path.of(p)))
                .filter(p -> commandIsOnPath(p, args))
                .findFirst();

        if (commandPath.isPresent()) {
            System.out.printf("%s is %s%n", args, commandPath.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean commandIsOnPath(String path, String command) {
        String[] pathSections = path.split("/");
        return command.equals(pathSections[pathSections.length - 1]);
    }
}
