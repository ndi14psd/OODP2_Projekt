package controller.command;

import java.util.List;

public class CommandComposite implements Command {

    private final List<Command> commands;

    public CommandComposite(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        for (Command command : commands) {
            command.undo();
        }
    }
}
