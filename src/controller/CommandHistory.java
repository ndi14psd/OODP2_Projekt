package controller;

import controller.command.Command;

import java.util.LinkedList;

class CommandHistory {
    private final LinkedList<Command> undoStack;
    private final LinkedList<Command> redoStack;

    CommandHistory() {
        undoStack = new LinkedList<>();
        redoStack = new LinkedList<>();
    }

    void addToHistory(Command command) {
        redoStack.clear();
        undoStack.push(command);
    }

    void undoLast() {
        if(!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            redoStack.push(command);
            command.undo();
        }
    }

    void redoLast() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            undoStack.push(command);
            command.execute();
        }
    }

}
