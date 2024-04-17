package manager.Command;

import exceptions.WrongArgumentException;
import manager.CollectionManager;
/**
 * Данная команда позваляет вывести коллекцию
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class ShowCommand implements BaseCommand {

    @Override
    public void execute(String[] args) throws Exception{
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        collectionManager.showCollection();
    }


    @Override
    public String getName() {
        return "show: ";
    }

    @Override
    public String getDescription() {
        return "Show collection";
    }
}
