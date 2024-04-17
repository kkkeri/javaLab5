package manager.Command;


import collection.Flat;
import exceptions.NoArgumentException;
import exceptions.NoElementException;
import exceptions.WrongArgumentException;
import generators.FlatGenerator;
import manager.CollectionManager;

import java.util.Date;
import java.util.LinkedHashSet;

/**
 * Данная команда обновляет значения полей элемента по его ID
 *
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class UpdateIdCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception{
        try {
            CollectionManager collectionManager = CollectionManager.getInstance();
            if (args.length > 2) {
                throw new WrongArgumentException(args[2]);
            }
            if (args.length < 2) {
                throw new NoArgumentException("id");
            }
            LinkedHashSet<Flat> flatCollection = collectionManager.getCollection();
            Flat referenceFlat = null;
            long inputEl = Long.parseLong(args[1]);
            boolean found = false;
            Date dateForUpdate = null;
            for (Flat flat : flatCollection) {
                if (flat.getId() == inputEl) {
                    dateForUpdate = flat.getCreationDate();
                    collectionManager.RemoveId(inputEl);
                    referenceFlat = FlatGenerator.createFlat();
                    referenceFlat.setIdForUpdate(inputEl);
                    referenceFlat.setCreationDate(dateForUpdate);
                    collectionManager.add(referenceFlat);
                    found = true;
                    System.out.println("Success");
                    break;
                }
            }
            if (!found){
                throw new NoElementException(inputEl);
            }
        } catch (NoElementException e) {
            System.out.println(e.getMessage());
            System.out.println("The program continues safely");
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter digit");
        }
    }

    @Override
    public String getName() {
        return "updateId {element}: ";
    }

    @Override
    public String getDescription() {
        return "Update element";
    }
}
