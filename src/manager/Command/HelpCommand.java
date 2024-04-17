package manager.Command;
import java.util.LinkedHashMap;
import manager.*;

/**
 * Данная команда выводит все команды и краткое их содержание
 *
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class HelpCommand implements BaseCommand{

    @Override
    public void execute(String[] args) {
        CommandManager commandManager = new CommandManager();
        LinkedHashMap<String, BaseCommand> commandList = commandManager.getCommandList();
        for (String name : commandList.keySet()) {
            BaseCommand command = commandList.get(name);
            System.out.println(command.getName() +command.getDescription());
        }
    }

    @Override
    public String getName() {
        return "help: ";
    }

    @Override
    public String getDescription() {
        return "Command to get information";
    }
}
