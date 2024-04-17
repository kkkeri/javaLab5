package manager.Command;

import collection.Flat;
import exceptions.NoElementException;
import exceptions.WrongArgumentException;
import manager.CollectionManager;

import java.util.LinkedHashSet;
/**
 * Данная команда позваляет вывести данные коллекции, в которых названеи квартиры содержит заданную подстроку
 *
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class FilterContainsNameCommand implements BaseCommand {

    public void execute(String[] args) throws Exception {
        CollectionManager collectionManager = CollectionManager.getInstance();
        LinkedHashSet<Flat> nameCollection = collectionManager.getCollection();
        try {
            if (args.length > 2) {
                throw new WrongArgumentException(args[2]);
            }

            if (args.length < 2) {
                throw new NoElementException("name");
            }
            String enterName = args[1];
            boolean found = false;
            for (Flat flat: nameCollection){
                if (flat.getName().contains(enterName)){
                    System.out.println(flat);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No flats names containing: " + enterName);
            }
        } catch (NoElementException e) {
            System.out.println(e.getMessage());
            System.out.println("You didn't enter the name of the flat");
        }
    }
    @Override
    public String getName() {
        return "filter_contains_name {name}: ";
    }

    @Override
    public String getDescription() {
        return "Displays flats whose names contain the specified substring.";
    }
}
