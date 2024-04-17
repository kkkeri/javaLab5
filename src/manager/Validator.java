package manager;
import collection.*;
import exceptions.WrongArgumentException;
import generators.GeneratorID;

import java.util.Iterator;
import java.util.LinkedHashSet;
/**
 * Класс для проверки на валидность полей объекта Vehicle и для проверки входных данных из файла
 *
 * @author keri
 * @since 1.0
 */
public class Validator {

    LinkedHashSet<Flat> flatList;
    public Validator(LinkedHashSet<Flat> flatList){
        this.flatList = flatList;
    }
    public static void validateName(String arg, String data) throws WrongArgumentException{
        if (arg.isEmpty() || arg.trim().isEmpty()){
            throw new WrongArgumentException(data);
        }
    }
    public static void coordinateX(String arg) throws WrongArgumentException{
        long x = Long.parseLong(arg);
        if (x > 668 || x >= Long.MAX_VALUE ) {
            throw new WrongArgumentException("x");
        }
    }
    public static void coordinatesY(String arg) throws WrongArgumentException{
        Double y = Double.parseDouble(arg);
        if (y > 431 || y >= Double.MAX_VALUE || y == null){
            throw new WrongArgumentException("y");
        }
    }

    public static void validateArea(String arg) throws WrongArgumentException{
        long area = Long.parseLong(arg);
        if (area >= 672 || area < 0 ) {
            throw new WrongArgumentException("area");
        }
    }

    public static void validatenumberOfRooms(String arg) throws WrongArgumentException{
        Integer numberOfRooms = Integer.parseInt(arg);
        if (numberOfRooms < 0 || numberOfRooms >= Integer.MAX_VALUE ) {
            throw new WrongArgumentException("number of rooms");
        }
    }
    public static void validateFurnish(String arg) throws WrongArgumentException{
        try {
            Furnish.valueOf(arg.toUpperCase());
        } catch (Exception e){
            throw new WrongArgumentException("furnish");
        }
    }
    public static void validateView(String arg) throws WrongArgumentException{
        try {
            View.valueOf(arg.toUpperCase());
        } catch (Exception e){
            throw new WrongArgumentException("view");
        }
    }
    public static void validateTransport(String arg) throws WrongArgumentException{
        try {
            Transport.valueOf(arg.toUpperCase());
        } catch (Exception e){
            throw new WrongArgumentException("transport");
        }
    }
    public static void validatename(String arg) throws WrongArgumentException{
        if (arg.isEmpty() || arg.trim().isEmpty()){
            throw new WrongArgumentException("name");
        }
    }
    public static void validateYear(String arg) throws WrongArgumentException{
        Integer year = Integer.parseInt(arg);
        if (year < 0 || year >= Integer.MAX_VALUE ) {
            throw new WrongArgumentException("year");
        }
    }
    public static void validatenumberOfFlatsOnFloor(String arg) throws WrongArgumentException{
        Long numberOfFlatsOnFloor = Long.parseLong(arg);
        if (numberOfFlatsOnFloor < 0 || numberOfFlatsOnFloor >= Long.MAX_VALUE ) {
            throw new WrongArgumentException("number of flats on floor");
        }
    }
    public static boolean validateCoordinates(Coordinates coordinates) {
        if (coordinates.getX() > 668) return false;
        if (coordinates.getY() == null || coordinates.getY() > 431) return false;
        return true;
    }

    public static boolean validateHouse(House house) {
        if (house.getYear() <= 0) return false;
        if (house.getNumberOfFlatsOnFloor() == null || house.getNumberOfFlatsOnFloor() <= 0) return false;
        return true;
    }
    public LinkedHashSet<Flat> validateFile() {
        for(Iterator<Flat> iterator = flatList.iterator(); iterator.hasNext();) {
            Flat flat = iterator.next();
            if((flat.getId() > 0) && ((GeneratorID.idIsUnique(flat.getId())))) {
                GeneratorID.add(flat.getId());
            }else {
                iterator.remove();
                System.out.println("id must be greater than 0, unique and as an integer");
                System.out.println("element from the input file was removed");
            }
            if(flat.getName() == null || flat.getName().isEmpty()) {
                iterator.remove();
                System.out.println("name cannot be empty or null");
                System.out.println("element from the input file was removed");
            }
            if(flat.getCoordinates() == null) {
                iterator.remove();
                System.out.println("coordinates cannot be null");
                System.out.println("element from the input file was removed");
            }

            if (flat.getArea() > 672  || flat.getArea() < 0) {
                iterator.remove();
                System.out.println("Area cannot be less then 0 and more than 672");
                System.out.println("element from the input file was removed");
            }
            if (flat.getNumberOfRooms() <= 0) {
                iterator.remove();
                System.out.println("Area cannot be less then 0");
                System.out.println("element from the input file was removed");
            }
            if (flat.getFurnish() == null) {
                iterator.remove();
                System.out.println("Furnish cannot be null");
                System.out.println("element from the input file was removed");
            }
            if (flat.getTransport() == null) {
                iterator.remove();
                System.out.println("Transport cannot be null");
                System.out.println("element from the input file was removed");
            }
            if (flat.getView() == null) {
                iterator.remove();
                System.out.println("View cannot be null");
                System.out.println("element from the input file was removed");
            }
            if (flat.getHouse() == null) {
                iterator.remove();
                System.out.println("House cannot be null");
                System.out.println("element from the input file was removed");
            }
            if (flat.getHouse() == null) {
                iterator.remove();
                System.out.println("Furnish cannot be null");
                System.out.println("element from the input file was removed");
            }
            if (flat.getHouse().getName() == null) {
                iterator.remove();
                System.out.println("Name cannot be null");
                System.out.println("element from the input file was removed");
            }
            if (flat.getHouse().getYear() <= 0) {
                iterator.remove();
                System.out.println("Year cannot be less then 0");
                System.out.println("element from the input file was removed");
            }
            if (flat.getHouse().getNumberOfFlatsOnFloor() <= 0) {
                iterator.remove();
                System.out.println("Number Of Flats On Floor cannot be less then 0");
                System.out.println("element from the input file was removed");
            }

            if(flat.getCoordinates().getX() > 668) {
                iterator.remove();
                System.out.println("X cannot be more then 668 and as an long");
                System.out.println("element from the input file was removed");
            }
            if(flat.getCoordinates().getY() >= 431 || flat.getCoordinates().getY() == null) {
                iterator.remove();
                System.out.println("Y cannot be less then 431 and as an Double");
                System.out.println("element from the input file was removed");
            }
        }
        return flatList;
    }
}

