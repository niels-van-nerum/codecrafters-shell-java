package shell.commands;

import shell.Utils;

public class Type implements Command {

    @Override
    public void execute(String args) {
        try {
            Class<? extends Command> commandClass = Class.forName("shell.commands." + Utils.capitalize(args)).asSubclass(Command.class);
            Command command = commandClass.getDeclaredConstructor().newInstance();
            CommandType type = command.getCommandType();
            System.out.printf("%s is a shell %s%n", args, type.toString().toLowerCase());
        } catch (ClassNotFoundException e) {
            System.out.printf("%s: not found%n", args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.builtin;
    }
}
