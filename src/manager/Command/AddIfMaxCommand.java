package manager.Command;

import Comparators.AreaComparator;
import Comparators.NumberOfFlatsOnFloorComparator;
import Comparators.NumberOfRoomsComparator;
import collection.Flat;
import exceptions.WrongArgumentException;
import generators.FlatGenerator;
import manager.CollectionManager;

import java.util.LinkedHashSet;
/**
 * Данная команда добавляет элемент в коллекцию, если больше всех в коллекции
 *
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */

public class AddIfMaxCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception {
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        LinkedHashSet<Flat> flatCollection = collectionManager.getCollection();
        AreaComparator comparator = new AreaComparator();
        NumberOfRoomsComparator comparator1 = new NumberOfRoomsComparator();
        NumberOfFlatsOnFloorComparator comparator2 = new NumberOfFlatsOnFloorComparator();
        Flat referenceFlat = FlatGenerator.createFlat();
        if(flatCollection.isEmpty() || (flatCollection.stream().allMatch(flat -> comparator.compare(flat,referenceFlat) < 0) && flatCollection.stream().allMatch(flat -> comparator1.compare(flat,referenceFlat) <0) && flatCollection.stream().allMatch(flat -> comparator2.compare(flat,referenceFlat) < 0))) {
            collectionManager.add(referenceFlat);
            System.out.println("successfully added");
        } else {
            System.out.println("Element lower then max");
        }
    }

    @Override
    public String getName() {
        return "add_if_max {element}: ";
    }

    @Override
    public String getDescription() {
        return "Adds if more";
    }
}
