package manager.Command;
import manager.CommandManager;
/**
 * Данная команда позваляет вывести последние 8 команд
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class HistoryCommand implements BaseCommand {
    @Override
    public void execute(String[] args) throws Exception {
        String[] sp = new String[8];
        int n = 0;
        for (BaseCommand command : CommandManager.lastEightCommand) {
            sp[n] = command.getName();
            n += 1;
        }
        for (int i = 0; i < 8; i++) {
            if (!(sp[i] == null)) {
                System.out.println("-" + sp[i]);
            }
        }
    }

    @Override
    public String getName() {
        return "history: ";
    }

    @Override
    public String getDescription() {
        return "Show last 8 command";
    }
}