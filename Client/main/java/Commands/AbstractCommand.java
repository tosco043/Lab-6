package Commands;

import java.io.IOException;

public abstract class AbstractCommand{
    public abstract void aboutCommand();

    public abstract void execute(String[] args) throws IOException;
}

//The class the sets the execution to false all things been equal
