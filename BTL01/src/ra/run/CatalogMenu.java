package ra.run;

import ra.color.ColorManager;
import ra.entity.Book;
import ra.entity.Category;
import ra.file.FileManager;

import java.util.Comparator;
import static ra.run.Library.categoryList;
import static ra.run.Library.bookList;
import static ra.run.Library.scanner;
public class CatalogMenu {
    public static void catalogMenu() {
            // đọc dữ liệu từu file
            do {
                System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
                System.out.println("⎜                        "+ColorManager.TIM+"QUẢN LÝ THỂ LOẠI"+ColorManager.RESET+"                        ⎜");
                System.out.println("┼✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧┼");
                System.out.println("⎜ "+ColorManager.YELLOW+"➣ 1."+ColorManager.RESET+" ⎜    "+ColorManager.CYAN+"Thêm mới thể loại"+ColorManager.RESET+"                                    ⎜");
                System.out.println("⎜ "+ColorManager.YELLOW+"➣ 2."+ColorManager.RESET+" ⎜    "+ColorManager.CYAN+"Hiển thị danh sách theo tên A – Z"+ColorManager.RESET+"                    ⎜");
                System.out.println("⎜ "+ColorManager.YELLOW+"➣ 3."+ColorManager.RESET+" ⎜    "+ColorManager.CYAN+"Thống kê thể loại và số sách có trong mỗi thể loại"+ColorManager.RESET+"   ⎜");
                System.out.println("⎜ "+ColorManager.YELLOW+"➣ 4."+ColorManager.RESET+" ⎜    "+ColorManager.CYAN+"Cập nhật thể loại"+ColorManager.RESET+"                                    ⎜");
                System.out.println("⎜ "+ColorManager.YELLOW+"➣ 5."+ColorManager.RESET+" ⎜    "+ColorManager.CYAN+"Xóa thể loại"+ColorManager.RESET+"                                         ⎜");
                System.out.println("⎜ "+ColorManager.YELLOW+"➣ 6."+ColorManager.RESET+" ⎜    "+ColorManager.CYAN+"Quay lại"+ColorManager.RESET+"                                             ⎜");
                System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
                System.out.print(ColorManager.YELLOW + "  ➣ Chọn : \t\t " + ColorManager.RESET);
                try {
                    int choiceCatalog = Integer.parseInt(scanner.nextLine());
                    switch (choiceCatalog){
                        case 1:
                            addCatalog();
                            FileManager.writeDataToCategoryFile(categoryList);
                            break;
                        case 2:
                            displayCatalogASC();
                            break;
                        case 3:
                            staticCatalog();
                            break;
                        case 4:
                           updateCatalog();
                            FileManager.writeDataToCategoryFile(categoryList);
                           break;
                        case 5:
                            deleteCatalog();
                            FileManager.writeDataToCategoryFile(categoryList);
                            break;
                        case 6:
                            Library.libraryMenu();
                        default:
                            System.out.println(ColorManager.RED+"Vui lòng chọn từ 1 đến 6."+ColorManager.RESET);
                    }
                } catch (NumberFormatException ex1){
                    System.out.println(ColorManager.RED+"Lỗi định dạng kiểu chữ. Vui lòng nhập lại."+ColorManager.RESET);
                }
            } while (true);
        }
        //Thêm mới thể loại sách
        public static void addCatalog(){
            System.out.println(ColorManager.GREEN+"Nhập vào số lượng thể loại :"+ColorManager.RESET);
            do {
                try {
                    int n = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < n; i++) {
                        System.out.println(ColorManager.TIM+"✦✦✦✦✦✦✦✦✦✦✦ Nhập thông tin thể loại sách thứ " + (i + 1) + " ✦✦✦✦✦✦✦✦✦✦✦"+ColorManager.RESET);
                        Category category = new Category();
                        category.input();
                        categoryList.add(category);
                    }
                    System.out.println();
                    System.out.println();
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(ColorManager.RED+"Đầu vào phải là kiểu số nguyên. Vui lòng nhập lại."+ColorManager.RESET);
                } catch (Exception exception){
                    System.out.println(ColorManager.RED+"Xảy ra lỗi"+exception.getMessage()+" ❗️ VUi lòng liên hệ tới hệ thống."+ColorManager.RESET);
                }
            }while (true);
        }
        // Hiển thị thể loại sách theo thứ tự A-Z
        public static void displayCatalogASC(){
            System.out.println();
            System.out.println();
        categoryList.stream().sorted(Comparator.comparing(Category::getName)).forEach(category -> category.output());
            System.out.println();
            System.out.println();
        }
        //Thống kê thể loại và số sách có trong mỗi thể loại
        public static void staticCatalog(){
            System.out.println();
            System.out.println(ColorManager.TIM+"✧✧✧✧✧✧✧✧✧✧✧ THỐNG KÊ THỂ LOẠI VÀ SỐ SÁCH ✧✧✧✧✧✧✧✧✧✧✧✧✧"+ColorManager.RESET);
            for (Category category : categoryList) {
        int cntBook = (int) bookList.stream().filter(book -> book.getCatalogId() == category.getId()).count();
                System.out.println(ColorManager.CYAN+"\tThể loại : "+ColorManager.RESET+ColorManager.YELLOW+category.getName()+ColorManager.RESET+ColorManager.CYAN+"\t-\t Số sách : "+ColorManager.RESET+ColorManager.YELLOW + cntBook+ColorManager.RESET);
            }
            System.out.println();
            System.out.println();
        }
        //Cập nhật thể loại
    public static void updateCatalog(){
        System.out.println();
        // Hiển thị danh sách thể loại để người dùng chọn
        displayCatalogASC();
        // nhập vào mã thể loại cần cập nhật
        System.out.println(ColorManager.GREEN+"Nhập vào mã thể loại cần cập nhât :"+ColorManager.RESET);
        int updateCatalogId = Integer.parseInt(scanner.nextLine());
        boolean isExistCatalogId = false;
        for (Category cat: categoryList) {
            if (cat.getId() == updateCatalogId){
                isExistCatalogId = true;
                cat.setName(Category.validateCatalogName());
                cat.setStatus(Category.validateCatalogStatus());
                break;
            }
        }
        if (!isExistCatalogId){
            System.out.println(ColorManager.RED+"Không tìm thấy mã thể loại cần cập nhật."+ColorManager.RESET);
        } else {
            System.out.println(ColorManager.GREEN+"Đã cập nhật thành công ✔︎"+ColorManager.RESET);
        }
        System.out.println();
        System.out.println();
    }
    public static void deleteCatalog(){
        System.out.println();
        //Hiển thị danh sách thể loại cho người dùng xoá
        displayCatalogASC();
        //nhập mã thể loại cần xoá
        System.out.println(ColorManager.GREEN+"Nhập vào mã thể loại cần xoá :"+ColorManager.RESET);
        int catalogIdDel = Integer.parseInt(scanner.nextLine());
        // check xem trong thể loại có sách hay chưa
        boolean isHasBooksReferencingCategory = false;
        for (Book book: bookList) {
            if (book.getCatalogId() == catalogIdDel){
                isHasBooksReferencingCategory = true;
                break;
            }
        }
        if (isHasBooksReferencingCategory){
            System.out.println(ColorManager.RED + "Thể loại có sách. Không thế xoá thể loại này." +ColorManager.RESET);
            System.out.println(ColorManager.RED+"Để xoá được thể loại sách cần xoá hết sách trong thể loại. Vui lòng chọn xoá sách trong MENU QUẢN LÝ SÁCH."+ColorManager.RESET);
        } else{
            boolean isCheckCatalogDel = false;
            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getId() == catalogIdDel){
                    isCheckCatalogDel = true;
                    categoryList.remove(i);
                    break;
                }
            }
            if (!isCheckCatalogDel){
                System.out.println(ColorManager.RED+"Không tìm thấy thể loại cần xoá. Vui lòng thao tác lại❗"+ColorManager.RESET);
            } else {
                System.out.println("Đã xoá thể loại " + catalogIdDel +" thành công ✔︎");
            }
        }
        System.out.println();
        System.out.println();
    }
}

