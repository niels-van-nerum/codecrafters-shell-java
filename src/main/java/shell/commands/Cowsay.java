package shell.commands;

public class Cowsay implements Command {

    @Override
    public void execute(String args) {
        System.out.printf("The cow says: %s", args);
    }

    @Override
    public CommandType getCommandType() {
        return null;
    }
}
