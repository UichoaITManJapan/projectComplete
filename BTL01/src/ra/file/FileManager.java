package ra.file;

import ra.color.ColorManager;
import ra.entity.Book;
import ra.entity.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String CATEGORY_FILE = "categories.txt";
    private static final String BOOK_FILE = "books.txt";
    public static <T> void saveDataToFile(String fileName, List<T> dataList){
        File file = new File(fileName);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(dataList);
            oos.flush();
        } catch (FileNotFoundException e) {
            System.out.println(ColorManager.RED+"File không tồn tại."+ColorManager.RESET);
        } catch (IOException e) {
            System.out.println(ColorManager.RED+"Lỗi khi ghi dữ liệu ra file."+ColorManager.RESET);
        }catch (Exception e){
            System.out.println(ColorManager.RED+"Xảy ra lỗi trong quá trình ghi ra file."+ColorManager.RESET);
        }finally {
            try {
                if (oos != null){
                    oos.close();
                }
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e){
                System.out.println(ColorManager.RED+"Xảy ra lỗi khi đóng các strem."+ColorManager.RESET);
            } catch (Exception e){
                System.out.println(ColorManager.RED+"Xảy ra lỗi trong quá trình đóng các stream."+ColorManager.RESET);
            }
        }
    }
    public static <T> List<T> loadDataFromFile(String fileName){
        File file = new File(fileName);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<T> dataList = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0){
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                dataList = (List<T>) ois.readObject();
            }
        } catch (FileNotFoundException e) {
            System.out.println(ColorManager.RED+"Không tồn tại file."+ColorManager.RESET);
        } catch (IOException e) {
            System.out.println(ColorManager.RED+"Lỗi khi đọc file."+ColorManager.RESET);
        } catch (ClassNotFoundException e) {
            System.out.println(ColorManager.RED+"Có lỗi trong quá trình đọc dữ liệu từ file."+ColorManager.RESET);
        } finally {
            try {
                if (fis != null){
                    fis.close();
                }
                if (ois != null){
                    ois.close();
                }
            } catch (IOException e){
                System.out.println(ColorManager.RED+"Có lỗi khi đóng stream."+ColorManager.RESET);
            } catch (Exception e){
                System.err.println(ColorManager.RED+"Có lỗi trong quá trình đóng các stream."+ColorManager.RESET);
            }
            return dataList;
        }
    }
    public static void writeDataToCategoryFile(List<Category> categoryList){
        saveDataToFile(CATEGORY_FILE,categoryList);
    }
    public static List<Category> readDataFromCategoryFile(){
        return loadDataFromFile(CATEGORY_FILE);
    }

    public static void writeDataToBookFile(List<Book> bookList){
        saveDataToFile(BOOK_FILE,bookList);
    }
    public static List<Book> readDataFromBookFile(){
        return loadDataFromFile(BOOK_FILE);
    }
}
