package manager.Command;

import exceptions.WrongArgumentException;
import generators.FlatGenerator;
import manager.CollectionManager;
/**
 * Данная команда добавляет новый элемент в коллекцию
 *
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */

public class AddCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception {
        FlatGenerator flatGenerator = new FlatGenerator();
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1){
            throw new WrongArgumentException(args[1]);
        }
        collectionManager.add(flatGenerator.createFlat());
        System.out.println("successfully added");
    }

    @Override
    public String getName() {
        return "add {element}: ";
    }

    @Override
    public String getDescription() {
        return "Add new flat in collection";
    }
}
