package manager.Command;


import exceptions.WrongArgumentException;
import manager.CollectionManager;
/**
 * Данная команда позваляет вывести информацию о коллекции
 *
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class InfoCommand implements BaseCommand {
    @Override
    public void execute(String[] args) throws Exception {
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1){
            throw new WrongArgumentException(args[1]);
        }
        collectionManager.InfoCollection();
    }

    @Override
    public String getName() {
        return "info: ";
    }

    @Override
    public String getDescription() {
        return "Informations about collection";
    }
}
