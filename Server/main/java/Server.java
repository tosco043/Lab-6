import Utilities.CollectionManager;
import Utilities.Logging;
import Utilities.ServerController;

import java.util.logging.Level;

public class Server {
    public static void main(String[] args) {
        try {
            String fileName = args[0];
            String port = args[1];
            Logging.log(Level.WARNING, "Port must be int number! Please notice about it!");
            CollectionManager.setFileName(fileName);
            System.out.println("hi there");
            CollectionManager.readInputFromJsonFile();
            Runtime.getRuntime().addShutdownHook(new Thread(CollectionManager::save));
            ServerController serverController = new ServerController(port);
            serverController.run();
        } catch(ArrayIndexOutOfBoundsException e){
            Logging.log(Level.INFO, "Invalid fileName and port");
        }
    }
}
