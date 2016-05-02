package commands;

import org.crsh.cli.Command;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;


@Usage("Hello World")
public class Hello extends BaseCommand{

    @Usage("Hello?")
    @Command
    public String helloWhat(){
        return "World!";
    }
}