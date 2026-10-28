package shell.commands;

public class Echo implements Command {

    @Override
    public void execute(String args) {
        System.out.println(args);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.builtin;
    }
}
