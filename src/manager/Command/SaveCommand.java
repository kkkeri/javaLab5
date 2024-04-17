package manager.Command;

import exceptions.WrongArgumentException;
import manager.CollectionManager;
import manager.FileManager;
import static manager.Console.filename;
/**
 * Данная команда позваляет сохранить изменения внесенную в коллекцию
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class SaveCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception {
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        FileManager fileManager = FileManager.getInstance(filename);
        fileManager.writeToFile(collectionManager.getCollection());
        System.out.println("Successfully saved");
    }

    @Override
    public String getName() {
        return "save: ";
    }

    @Override
    public String getDescription() {
        return "Save collection to json file";
    }
}
