package manager.Command;

import exceptions.NoElementException;
import exceptions.WrongArgumentException;
import generators.GeneratorID;
import manager.CollectionManager;
/**
 * Данная команда позваляет удалить элемент коллекции по ID
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class RemoveIdCommand implements BaseCommand{
    @Override
    public void execute(String args[]) throws Exception {
        CollectionManager collectionManager = CollectionManager.getInstance();
        try {
            if (args.length > 2) {
                throw new WrongArgumentException(args[2]);
            }

            if (args.length < 2) {
                throw new NoElementException("id");
            }
            long id = Long.parseLong(args[1]);
            collectionManager.RemoveId(Long.parseLong((args[1])));
            GeneratorID.remove(Long.parseLong(args[2]));
        } catch (NoElementException e) {
            System.out.println(e.getMessage());
            System.out.println("There is no such id");
        }
    }
    @Override
    public String getName() {
        return "rbid: ";
    }

    @Override
    public String getDescription() {
        return "Remove by id";
    }
}

