package manager;
import collection.*;
import exceptions.NoElementException;
import exceptions.WrongArgumentException;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
/**
 * Класс отвечает за взаимодействие с коллекцией на базовом уровне
 *
 * @see Flat
 * @author keri
 * @since 1.0
 */
public class CollectionManager {
    private static CollectionManager instance;

    private static LinkedHashSet<Flat> flatCollection;
    private final java.util.Date creationDate;

    private CollectionManager() {
        flatCollection = new LinkedHashSet<>();
        creationDate = new Date();
    }

    public static CollectionManager getInstance() {
        if (instance == null) {
            instance = new CollectionManager();
        }
        return instance;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void add(Flat flat) {
        flatCollection.add(flat);
    }

    public LinkedHashSet<Flat> getCollection() {
        return flatCollection;
    }

    public void setFlatCollection(LinkedHashSet<Flat> flatCollection) {
        this.flatCollection = flatCollection;
    }

    public void showCollection() {
        for (Flat flat : flatCollection) {
            System.out.println(flat);
        }

    }

    public void InfoCollection() {
        System.out.println("type-" + flatCollection.getClass() + "date-" + getCreationDate() + "size-" + flatCollection.size());
    }

    public Flat RemoveId(long id) throws NoElementException{
        Iterator<Flat> iterator = flatCollection.iterator();
        while (iterator.hasNext()) {
            Flat flat = iterator.next();
            if (flat.getId() == id) {
                iterator.remove();
                return flat;
            }
        }
        throw new NoElementException(id);
    }

    public void clearCollection() { flatCollection.clear();}

}
