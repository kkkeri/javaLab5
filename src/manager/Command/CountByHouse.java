package manager.Command;

import collection.Flat;
import collection.House;
import exceptions.NoElementException;
import exceptions.WrongArgumentException;
import manager.CollectionManager;

import java.util.LinkedHashSet;
/**
 * Данная команда cчитает коллекции с одинаковым именем
 *
 * @see BaseCommand
 * @author kei
 * @since 1.0
 */
public class CountByHouse implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception {
        CollectionManager collectionManager = CollectionManager.getInstance();
        LinkedHashSet<Flat> houseCollection = collectionManager.getCollection();
        try {
            if (args.length > 2) {
                throw new WrongArgumentException(args[2]);
            }

            if (args.length < 2) {
                throw new NoElementException("house");
            }
            String enterName = args[1];
            int count = 0;
            for (Flat flat: houseCollection){
                if (enterName.equals(flat.getHouse().getName()))
                {
                    count += 1;
                }
            }

        System.out.println("count: " + count);
        }catch (NoElementException e) {
            System.out.println(e.getMessage());
            System.out.println("You didn't enter the name of the house");
        }

    }

    @Override
    public String getName() {
        return "count_by_house: ";
    }

    @Override
    public String getDescription() {
        return "Displays the number of houses with the same name";
    }
}
