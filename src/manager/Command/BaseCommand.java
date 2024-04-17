package manager.Command;
/**
 * Интерфейс для реализации команд.
 *
 * @author keri
 * @since 1.0
 */
public interface BaseCommand {
    public void execute(String[] args) throws Exception;

    public String getName();

    public String getDescription();
}
