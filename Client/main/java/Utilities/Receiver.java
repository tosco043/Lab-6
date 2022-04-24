package Utilities;

import Commands.SerializableCommands.SerializedArgumentCommand;
import Commands.SerializableCommands.SerializedCombinedCommand;
import Commands.SerializableCommands.SerializedObjectCommand;
import Commands.SerializableCommands.SerializedSimplyCommand;
import Commands.SpecificCommands.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Receiver {
    private final Invoker invoker;
    private final Sender sender;
    private final CommandAsker commandAsker = new CommandAsker(new InputChecker());
    private final Communicator communicator;
    private DatagramChannel datagramChannel;
    private final HashMap<String, Boolean> inStack = new HashMap<>();


    public Receiver(Invoker invoker, Sender sender, Communicator communicator) {
        this.invoker = invoker;
        this.sender = sender;
        this.communicator = communicator;
    }

    private void setWriteChannel() throws IOException {
        sender.setDatagramChannel(communicator.selectWriteChannel());
    }

    public void help() {
        invoker.getCommands().forEach((name, command) -> command.aboutCommand());
    }

    public void info() throws IOException {
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new Info());
        setWriteChannel();
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if (datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void show() throws IOException {
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new Show());
        setWriteChannel();
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if (datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void exit() {
        System.exit(0);
    }

    public void clear() throws IOException {
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new Clear());
        setWriteChannel();
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if (datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void executeScript(String fileName) throws IOException {
        if (inStack.get(fileName) != null) {
            if (inStack.get(fileName)) {
                System.out.println("To avoid infinite recursion. File " + fileName + " can't be executed!");
                return;
            }
        }

        inStack.put(fileName, true);

        File scriptFile = new File(fileName);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(scriptFile);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Script file doesn't exist. Please check the file path!");
            return;
        }

        while (fileScanner.hasNextLine()) {
            String[] userCommand = fileScanner.nextLine().trim().split(" ");
            invoker.executeCommand(userCommand);
            System.out.println("--------------------------------------------------------------------");
        }

        inStack.put(fileName, false);
    }

    public void add() throws IOException {
        SerializedObjectCommand serializedObjectCommand = new SerializedObjectCommand(new Add(),
                commandAsker.createProduct());
        setWriteChannel();
        sender.sendObject(serializedObjectCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if (datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void removeByID(String arg) throws IOException {
        SerializedArgumentCommand serializedArgumentCommand = new SerializedArgumentCommand(new RemoveById(), arg);
        setWriteChannel();
        sender.sendObject(serializedArgumentCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if (datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);

    }

    public void update() throws IOException{
        SerializedCombinedCommand serializedCombinedCommand = new SerializedCombinedCommand(new Update(),
                commandAsker.idAsker(), commandAsker.createProduct());
        setWriteChannel();
        sender.sendObject(serializedCombinedCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void removeGreater() throws IOException{
        SerializedObjectCommand serializedObjectCommand =  new SerializedObjectCommand(new RemoveGreater(),
                commandAsker.createProduct());
        setWriteChannel();
        sender.sendObject(serializedObjectCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);

    }

    public void removeLower() throws IOException{
        SerializedObjectCommand serializedObjectCommand =  new SerializedObjectCommand(new RemoveLower(),
                commandAsker.createProduct());
        setWriteChannel();
        sender.sendObject(serializedObjectCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void minByOfficialAddress(String args) throws IOException {
        SerializedArgumentCommand serializedArgumentCommand = new SerializedArgumentCommand(
                new MinByOfficialAddress(), args);
        setWriteChannel();
        sender.sendObject(serializedArgumentCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);

    }

    public void countLessThanOfficialAddress(String args) throws IOException {
        SerializedArgumentCommand serializedArgumentCommand = new SerializedArgumentCommand(
                new CountLessThanOfficialAddress(), args);
        setWriteChannel();
        sender.sendObject(serializedArgumentCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }
    public void printFieldAscendingType() throws IOException {
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new PrintFieldAscendingType());
        setWriteChannel();
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if (datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }
}
