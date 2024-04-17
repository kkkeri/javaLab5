package generators;

import java.util.ArrayList;
/**
 * Класс генерирует ID для объекта Vehicle
 *
 * @author keri
 * @see collection.Flat
 * @since 1.0
 */
public class GeneratorID{
    private static final Integer min = 3;
    private static final Integer max = 100000;
    private static ArrayList<Long> IdList = new ArrayList<>();

    /**
     * Базовый конструктор
     *
     * @author keri
     * @since 1.0
     */
    public GeneratorID() {
        IdList = new ArrayList<>();
    }

    /**
     * Метод генерирует новый уникальный ID
     *
     * @author keri
     * @since 1.0
     */
    public static long generateId() {
        long id = (long) Math.floor(Math.random() * (max - min + 1)) + min;
        while (IdList.contains(id)) {
            id = (long) Math.floor(Math.random() * (max - min + 1)) + min;
        }
        IdList.add(id);
        return id;
    }

    /**
     * Метод проверяет ID на уникальность
     *
     * @param id какой id нужно проверить на уникальность
     * @author keri
     * @since 1.0
     */
    public static boolean idIsUnique(long id) {
        if (IdList.contains(id)) {
            return false;
        }
        return true;
    }

    /**
     * Добавляет ID в список
     *
     * @param id какой id нужно добавить
     * @author keri
     * @since 1.0
     */
    public static void add(long id) {
        IdList.add(id);
    }

    /**
     * Удаляет ID из списка
     *
     * @param id какой id нужно удалить
     * @author keri
     * @since 1.0
     */
    public static void remove(long id) {
        IdList.remove(id);
    }
}
