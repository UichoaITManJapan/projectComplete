package ra.run;

import ra.color.ColorManager;
import ra.entity.Book;
import ra.entity.Category;
import ra.file.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public  class Library {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Category> categoryList = new ArrayList<>();
    public static List<Book> bookList = new ArrayList<>();
    public static void main(String[] args) {
        categoryList = FileManager.readDataFromCategoryFile();
        bookList = FileManager.readDataFromBookFile();
        System.out.println(ColorManager.GREEN+"\tCHÀO MỪNG BẠN ĐẾN VỚI TRANG QUẢN LÝ THƯ VIỆN"+ColorManager.RESET);
        libraryMenu();
    }
    // quản lý thư viện
    public static void libraryMenu(){
        do {
            System.out.println();
            System.out.println("☥------------------------------------------------☥");
            System.out.println("⎜                 " + ColorManager.BLUE +"QUẢN LÝ THƯ VIỆN" + ColorManager.RESET +"               ⎜");
            System.out.println("☥------------------------------------------------☥");
            System.out.println("⎜  "+ColorManager.TIM+"1."+ColorManager.RESET+"  ⎜          "+ColorManager.TIM+"Quản lý Thể loại"+ColorManager.RESET+"               ⎜");
            System.out.println("⎜  "+ColorManager.XANHLAMA+"2."+ColorManager.RESET+"  ⎜          "+ColorManager.XANHLAMA+"Quản lý Sách"+ColorManager.RESET+"                   ⎜");
            System.out.println("⎜  "+ColorManager.DONHAT+"3."+ColorManager.RESET+"  ⎜          "+ColorManager.DONHAT+"Thoát"+ColorManager.RESET+"                          ⎜");
            System.out.println("☥------------------------------------------------☥");
            System.out.print(ColorManager.YELLOW +"   Chọn chức năng :\t " + ColorManager.RESET);
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                System.out.println();
                System.out.println();
                switch (choice){
                    case 1:
                        CatalogMenu.catalogMenu();
                        break;
                    case 2:
                       BookMenu.bookMenu();
                       break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println(ColorManager.RED+"Vui lòng chọn chức năng từ 1 đến 3."+ColorManager.RESET);
                }
            } catch (NumberFormatException ex1){
                System.out.println(ColorManager.RED+"Lỗi " +ex1.getMessage() + "❗️ Vui lòng nhập lại."+ColorManager.RESET);
            } catch (Exception exception){
                System.out.println(ColorManager.RED+"Xảy ra lỗi " + exception.getMessage() + "❗️ Vui lòng liên hệ tới hệ thống."+ColorManager.RESET);
            }
        } while (true);
    }


}
