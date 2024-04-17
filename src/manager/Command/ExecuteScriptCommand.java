package manager.Command;

import collection.*;
import generators.*;
import Comparators.*;
import exceptions.*;
import manager.CollectionManager;
import manager.CommandManager;
import manager.Validator;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
/**
 * Данная команда выполняет скрипт файл
 *
 * @see BaseCommand
 * @author keri
 * @since 1.0
 */
public class ExecuteScriptCommand implements BaseCommand {
    private static Stack<File> stack = new Stack<>();
    @Override
    public void execute(String[] args) throws Exception {
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 2) {
            throw new WrongArgumentException(args[2]);
        }
        if (args.length < 2) {
            throw new NoArgumentException("script name");
        }
        File file = new File(args[1]);
        try {
            if (!file.canRead()) {
                throw new RootException();
            }
            if (stack.isEmpty()) {
                stack.add(file);
            } else if (stack.contains(file)) {
                throw new RecursionException();
            }
            stack.add(file);
        } catch (RecursionException ex) {
            stack.pop();
            System.out.println(ex.getMessage());
            System.out.println("Script Executing finished with error");
            if (!stack.isEmpty()) {
                stack.pop();
            }
            return;
        }
        String path = args[1];
        Scanner scanner = new Scanner(new File(path), StandardCharsets.UTF_8.name());
        String[] flatANS = new String[11];
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if ((line.split(" ")[0].equals("add"))) {
                    try {
                        String[] parts = line.split(" ");
                        if (parts.length > 1) {
                            throw new WrongArgumentException(parts[2]);
                        }
                        for (int n = 0; n < 11 && scanner.hasNextLine(); n++) {
                            flatANS[n] = scanner.nextLine();
                        }
                        Flat flat1 = new Flat();
                        Validator.validateName(flatANS[0], "Name");
                        Validator.validateName(flatANS[1], "x");
                        Validator.coordinateX(flatANS[1]);
                        Validator.validateName(flatANS[2], "y");
                        Validator.coordinatesY(flatANS[2]);
                        Validator.validateName(flatANS[3], "Area");
                        Validator.validateArea(flatANS[3]);
                        Validator.validateName(flatANS[4], "Number of rooms");
                        Validator.validatenumberOfRooms(flatANS[4]);
                        Validator.validateName(flatANS[5], "Furnish");
                        Validator.validateFurnish(flatANS[5]);
                        Validator.validateName(flatANS[6], "View");
                        Validator.validateView(flatANS[6]);
                        Validator.validateName(flatANS[7], "Transport");
                        Validator.validateTransport(flatANS[7]);
                        Validator.validateName(flatANS[8], "name");
                        Validator.validatename(flatANS[8]);
                        Validator.validateName(flatANS[9], "Year");
                        Validator.validateYear(flatANS[9]);
                        Validator.validateName(flatANS[10], "Number of flats on floor");
                        Validator.validatenumberOfFlatsOnFloor(flatANS[10]);
                        Coordinates coordinates = new Coordinates(Long.parseLong(flatANS[1]), Double.parseDouble(flatANS[2]));
                        House house = new House(flatANS[8],Integer.parseInt(flatANS[9]),Long.parseLong(flatANS[10]));
                        flat1.setId(GeneratorID.generateId());
                        flat1.setName(flatANS[0]);
                        flat1.setCoordinates(coordinates);
                        flat1.setCreationDate(new Date());
                        flat1.setArea(Long.parseLong(flatANS[3]));
                        flat1.setNumberOfRooms(Integer.parseInt(flatANS[4]));
                        flat1.setFurnish(Furnish.valueOf(flatANS[5].toUpperCase()));
                        flat1.setView(View.valueOf(flatANS[6].toUpperCase()));
                        flat1.setTransport(Transport.valueOf(flatANS[7].toUpperCase()));
                        flat1.setHouse(house);
                        collectionManager.add(flat1);
                        System.out.println("New element successfully added");
                    } catch (WrongArgumentException exx) {
                        stack.pop();
                        System.out.println(exx.getMessage());
                        System.out.println("Script Executing finished with error");
                        return;
                    }
                } else if (line.split(" ")[0].equals("updateId")) {
                        try {
                            String[] parts = line.split(" ");
                            if (parts.length > 2) {
                                throw new WrongArgumentException(parts[2]);
                            }
                            if (parts.length < 2) {
                                throw new NoArgumentException("id");
                            }
                            String rightPart = parts[1];
                            for (int n = 0; n < 11 && scanner.hasNextLine(); n++) {
                                flatANS[n] = scanner.nextLine();
                            }
                            Flat referenceFlat = new Flat();
                            Validator.validateName(flatANS[0], "Name");
                            Validator.validateName(flatANS[1], "x");
                            Validator.coordinateX(flatANS[1]);
                            Validator.validateName(flatANS[2], "y");
                            Validator.coordinatesY(flatANS[2]);
                            Validator.validateName(flatANS[3], "Area");
                            Validator.validateArea(flatANS[3]);
                            Validator.validateName(flatANS[4], "Number of rooms");
                            Validator.validatenumberOfRooms(flatANS[4]);
                            Validator.validateName(flatANS[5], "Furnish");
                            Validator.validateFurnish(flatANS[5]);
                            Validator.validateName(flatANS[6], "View");
                            Validator.validateView(flatANS[6]);
                            Validator.validateName(flatANS[7], "Transport");
                            Validator.validateTransport(flatANS[7]);
                            Validator.validateName(flatANS[8], "name");
                            Validator.validatename(flatANS[8]);
                            Validator.validateName(flatANS[9], "Year");
                            Validator.validateYear(flatANS[9]);
                            Validator.validateName(flatANS[10], "Number of flats on floor");
                            Validator.validatenumberOfFlatsOnFloor(flatANS[10]);
                            Coordinates coordinates = new Coordinates(Long.parseLong(flatANS[1]), Double.parseDouble(flatANS[2]));
                            House house = new House(flatANS[8],Integer.parseInt(flatANS[9]),Long.parseLong(flatANS[10]));
                            LinkedHashSet<Flat> flatCollection = collectionManager.getCollection();
                            long inputEl = Long.parseLong(rightPart);
                            Date dateForUpdate = null;
                            boolean found = false;
                            for (Flat flat: flatCollection){
                                if (flat.getId() == inputEl){
                                    dateForUpdate = flat.getCreationDate();
                                    collectionManager.RemoveId(inputEl);
                                    referenceFlat.setIdForUpdate(inputEl);
                                    referenceFlat.setName(flatANS[0]);
                                    referenceFlat.setCoordinates(coordinates);
                                    referenceFlat.setCreationDate(dateForUpdate);
                                    referenceFlat.setArea(Long.parseLong(flatANS[3]));
                                    referenceFlat.setNumberOfRooms(Integer.parseInt(flatANS[4]));
                                    referenceFlat.setFurnish(Furnish.valueOf(flatANS[5].toUpperCase()));
                                    referenceFlat.setView(View.valueOf(flatANS[6].toUpperCase()));
                                    referenceFlat.setTransport(Transport.valueOf(flatANS[7].toUpperCase()));
                                    referenceFlat.setHouse(house);
                                    collectionManager.add(referenceFlat);
                                    System.out.println("Element successfully updated");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                throw new NoElementException(inputEl);
                            }
                            for (int v = 0; v < parts.length; v++) {
                                parts[v] = null;
                            }
                        } catch (WrongArgumentException exx) {
                            stack.pop();
                            System.out.println(exx.getMessage());
                            System.out.println("Script Executing finished with error");
                            return;
                        } catch (NoArgumentException exx) {
                            stack.pop();
                            System.out.println(exx.getMessage());
                            System.out.println("Script Executing finished with error");
                            return;
                        } catch (NoElementException ex) {
                            stack.pop();
                            System.out.println(ex.getMessage());
                            System.out.println("Script Executing finished with error");
                            return;
                        }
                } else if (line.split(" ")[0].equals("add_if_max")) {
                    try {
                        String[] parts = line.split(" ");
                        if (parts.length > 1) {
                            throw new WrongArgumentException(parts[1]);
                        }
                        for (int n = 0; n < 11 && scanner.hasNextLine(); n++) {
                            flatANS[n] = scanner.nextLine().toUpperCase();
                        }
                        Flat flat1 = new Flat();
                        Validator.validateName(flatANS[0], "Name");
                        Validator.validateName(flatANS[1], "x");
                        Validator.coordinateX(flatANS[1]);
                        Validator.validateName(flatANS[2], "y");
                        Validator.coordinatesY(flatANS[2]);
                        Validator.validateName(flatANS[3], "Area");
                        Validator.validateArea(flatANS[3]);
                        Validator.validateName(flatANS[4], "Number of rooms");
                        Validator.validatenumberOfRooms(flatANS[4]);
                        Validator.validateName(flatANS[5], "Furnish");
                        Validator.validateFurnish(flatANS[5]);
                        Validator.validateName(flatANS[6], "View");
                        Validator.validateView(flatANS[6]);
                        Validator.validateName(flatANS[7], "Transport");
                        Validator.validateTransport(flatANS[7]);
                        Validator.validateName(flatANS[8], "name");
                        Validator.validatename(flatANS[8]);
                        Validator.validateName(flatANS[9], "Year");
                        Validator.validateYear(flatANS[9]);
                        Validator.validateName(flatANS[10], "Number of flats on floor");
                        Validator.validatenumberOfFlatsOnFloor(flatANS[10]);
                        Coordinates coordinates = new Coordinates(Long.parseLong(flatANS[1]), Double.parseDouble(flatANS[2]));
                        House house = new House(flatANS[8], Integer.parseInt(flatANS[9]), Long.parseLong(flatANS[10]));
                        flat1.setId(GeneratorID.generateId());
                        flat1.setName(flatANS[0]);
                        flat1.setCoordinates(coordinates);
                        flat1.setCreationDate(new Date());
                        flat1.setArea(Long.parseLong(flatANS[3]));
                        flat1.setNumberOfRooms(Integer.parseInt(flatANS[4]));
                        flat1.setFurnish(Furnish.valueOf(flatANS[5].toUpperCase()));
                        flat1.setView(View.valueOf(flatANS[6].toUpperCase()));
                        flat1.setTransport(Transport.valueOf(flatANS[7].toUpperCase()));
                        flat1.setHouse(house);
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
                        for (int v = 0; v < parts.length; v++) {
                            parts[v] = null;
                        }
                    } catch (WrongArgumentException exx) {
                        stack.pop();
                        System.out.println(exx.getMessage());
                        System.out.println("Script Executing finished with error");
                        return;
                    }
                } else if (line.split(" ")[0].equals("add_if_min")) {
                    try {
                        String[] parts = line.split(" ");
                        if (parts.length > 1) {
                            throw new WrongArgumentException(parts[1]);
                        }
                        for (int n = 0; n < 11 && scanner.hasNextLine(); n++) {
                            flatANS[n] = scanner.nextLine().toUpperCase();
                        }
                        Flat flat1 = new Flat();
                        Validator.validateName(flatANS[0], "Name");
                        Validator.validateName(flatANS[1], "x");
                        Validator.coordinateX(flatANS[1]);
                        Validator.validateName(flatANS[2], "y");
                        Validator.coordinatesY(flatANS[2]);
                        Validator.validateName(flatANS[3], "Area");
                        Validator.validateArea(flatANS[3]);
                        Validator.validateName(flatANS[4], "Number of rooms");
                        Validator.validatenumberOfRooms(flatANS[4]);
                        Validator.validateName(flatANS[5], "Furnish");
                        Validator.validateFurnish(flatANS[5]);
                        Validator.validateName(flatANS[6], "View");
                        Validator.validateView(flatANS[6]);
                        Validator.validateName(flatANS[7], "Transport");
                        Validator.validateTransport(flatANS[7]);
                        Validator.validateName(flatANS[8], "name");
                        Validator.validatename(flatANS[8]);
                        Validator.validateName(flatANS[9], "Year");
                        Validator.validateYear(flatANS[9]);
                        Validator.validateName(flatANS[10], "Number of flats on floor");
                        Validator.validatenumberOfFlatsOnFloor(flatANS[10]);
                        Coordinates coordinates = new Coordinates(Long.parseLong(flatANS[1]), Double.parseDouble(flatANS[2]));
                        House house = new House(flatANS[8], Integer.parseInt(flatANS[9]), Long.parseLong(flatANS[10]));
                        flat1.setId(GeneratorID.generateId());
                        flat1.setName(flatANS[0]);
                        flat1.setCoordinates(coordinates);
                        flat1.setCreationDate(new Date());
                        flat1.setArea(Long.parseLong(flatANS[3]));
                        flat1.setNumberOfRooms(Integer.parseInt(flatANS[4]));
                        flat1.setFurnish(Furnish.valueOf(flatANS[5].toUpperCase()));
                        flat1.setView(View.valueOf(flatANS[6].toUpperCase()));
                        flat1.setTransport(Transport.valueOf(flatANS[7].toUpperCase()));
                        flat1.setHouse(house);
                        LinkedHashSet<Flat> flatCollection = collectionManager.getCollection();
                        AreaComparator comparator = new AreaComparator();
                        NumberOfRoomsComparator comparator1 = new NumberOfRoomsComparator();
                        NumberOfFlatsOnFloorComparator comparator2 = new NumberOfFlatsOnFloorComparator();
                        Flat referenceFlat = FlatGenerator.createFlat();
                        if(flatCollection.isEmpty() || (flatCollection.stream().allMatch(flat -> comparator.compare(flat,referenceFlat) > 0) && flatCollection.stream().allMatch(flat -> comparator1.compare(flat,referenceFlat) >0) && flatCollection.stream().allMatch(flat -> comparator2.compare(flat,referenceFlat) > 0))) {
                            collectionManager.add(referenceFlat);
                            System.out.println("successfully added");
                        } else {
                            System.out.println("Element greater then min");
                        }
                        for (int v = 0; v < parts.length; v++) {
                            parts[v] = null;
                        }
                    } catch (WrongArgumentException exx) {
                        stack.pop();
                        System.out.println(exx.getMessage());
                        System.out.println("Script Executing finished with error");
                        return;
                    }
                }else {
                    CommandManager.startExecuting(line);
                }
            }
            stack.pop();
            scanner.close();
            System.out.println("Script command executing complete!");
        } catch (EmptyStackException e) {
            System.out.print("");
        } finally {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
    }

    @Override
    public String getName() {
        return "execute_script {filename}: ";
    }

    @Override
    public String getDescription() {
        return "execute script from file";
    }
}


