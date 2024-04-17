package manager.Command;
/**
 * Данная команда позваляет прервать выполнение программы
 *
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class ExitCommand implements BaseCommand {
    @Override
    public void execute(String[] args) throws Exception {
        System.exit(1);
    }

    @Override
    public String getName() {
        return "exit: ";
    }

    @Override
    public String getDescription() {
        return "Close command without save";
    }
}