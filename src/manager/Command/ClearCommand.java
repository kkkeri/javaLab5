package manager.Command;

import exceptions.WrongArgumentException;
import manager.CollectionManager;
/**
 * Данная команда очищает коллекцию
 *
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class ClearCommand implements BaseCommand{
    public void execute(String[] args) throws Exception{
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1){
            throw new WrongArgumentException(args[1]);
        }
        if (collectionManager.getCollection().isEmpty()){
            System.out.println("Collection already cleared");
        } else {
            collectionManager.clearCollection();
            System.out.println("Collection clear");
        }
    }

    public String getName(){
        return "clear: ";
    }
    public String getDescription() {
        return "Command to clear colletion";
    }
}
