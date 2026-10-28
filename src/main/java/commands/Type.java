package commands;

public class Type implements Command {

    @Override
    public void execute(String args) {
        try {
            Class<? extends Command> commandClass = Class.forName(args).asSubclass(Command.class);
            Command command = commandClass.getDeclaredConstructor().newInstance();
            CommandType type = command.getCommandType();
            System.out.printf("%s is a shell %s", args, type.toString().toLowerCase());
        } catch (ClassNotFoundException e) {
            System.out.printf("%s: not found/n", args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.builtin;
    }
}
