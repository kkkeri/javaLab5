package manager;
import collection.Flat;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
/**
 * Класс, отвечающий за сериализацию и десериализацию в XML и обратно в коллекцию
 *
 * @author keri
 * @since 1.0
 */
public class FileManager {

    private static FileManager instance;
    private String filename;
    private static Gson gson = new Gson();

    public FileManager(String filename) {
        this.filename = filename;
    }
    public static FileManager getInstance(String filename){
        if (instance == null) {
            instance = new FileManager(filename);
        }
        return instance;
    }
    /**
     * Метод для записи коллекции в JSON файл
     *
     * @since 1.0
     */
    public void writeToFile(LinkedHashSet<Flat> collection) {
        // Опционально: Фильтрация коллекции, оставляем только валидные объекты
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename))) {
            String json = gson.toJson(collection); // Сериализация отфильтрованной коллекции
            writer.write(json); // Запись JSON в файл
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public LinkedHashSet<Flat> readFromFile() {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] data = bis.readAllBytes(); // Читаем все данные из файла
            String json = new String(data); // Преобразуем данные в строку JSON
            Type collectionType = new TypeToken<LinkedHashSet<Flat>>(){}.getType(); // Определяем тип
            LinkedHashSet<Flat> Startflats = gson.fromJson(json, collectionType); // Десериализация
            // Валидация каждого объекта после десериализации
            Validator validator = new Validator(Startflats);
            LinkedHashSet<Flat> flatLinkedHashSet = new LinkedHashSet<>();
            flatLinkedHashSet.addAll(validator.validateFile());
            return flatLinkedHashSet;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении из файла: " + e.getMessage());
            return new LinkedHashSet<>(); // Возвращаем пустую коллекцию
        } catch (JsonSyntaxException e) {
            System.out.println("Have a syntax error check the file");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("have an error in the number formats");
            System.exit(1);
        } catch (NullPointerException e) {
            System.out.println("Can't find a collection in file");
            System.exit(1);
        }
        catch (IllegalStateException e) {
            System.out.println("Unexpected error");
            System.exit(1);
        }
        catch (NoSuchElementException e) {
            System.out.println("File is empty");
            System.exit(1);
        }
        return null;
    }
}

