package Utilities;

import Commands.AbstractCommand;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Invoker {
    private final HashMap<String, AbstractCommand> commands = new HashMap<>();
    private  final Deque<String> commandHistory = new ArrayDeque<>();
    public void register(String name, AbstractCommand command){
        commands.put(name, command);
    }
    public void executeCommand(String[] userCommand) throws IOException {
        try {
            if (userCommand[0].equals("history")) {
                commandHistory.forEach(System.out::println);
            } else {
                AbstractCommand command = commands.get(userCommand[0]);
                command.execute(userCommand);
            }
            addCommandToHistory(userCommand[0]);
        } catch (NullPointerException e) {
            System.out.println("Command is not supported! Insert help to see guidelines");
        }
    }
    public void addCommandToHistory(String onlyName) {
        if (commandHistory.size() == 7) commandHistory.removeFirst();
        commandHistory.addLast(onlyName);
    }
    public HashMap<String, AbstractCommand> getCommands(){
        return this.commands;
    }
}
