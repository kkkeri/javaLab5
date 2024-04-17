package manager;

import java.io.InputStream;
import java.util.Scanner;
/**
 * Класс отвечает за чтение командной строки
 * Обеспечивает связь между пользователем и командами
 *
 * @see CommandManager
 * @author keri
 * @since 1.0
 */
public class Console {
 public static String filename;
    /**
     * Выполнение команды из InputStream
     *
     * @param inputStream откуда происходит чтение
     * @param args путь до файла
     */
 public void start(InputStream inputStream, String[] args) {
     Scanner scanner = new Scanner(inputStream);
     String namefile = args[0];
     CommandManager commandManager = new CommandManager();
     CollectionManager collectionManager = CollectionManager.getInstance();
     FileManager fileManager = FileManager.getInstance(namefile);
     collectionManager.setFlatCollection(fileManager.readFromFile());
     while (scanner.hasNextLine()){
         String command = scanner.nextLine().trim();
         if (!command.isEmpty()) try {
             commandManager.startExecuting(command);
         } catch (Exception e){
             System.out.println(e.getMessage());
         }
     }
 }
}
