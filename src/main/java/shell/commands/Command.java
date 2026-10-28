package shell.commands;

public interface Command {
    void execute(String args);

    CommandType getCommandType();
}
