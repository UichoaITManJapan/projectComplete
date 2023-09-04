package ra.run;
import ra.color.ColorManager;
import ra.entity.Book;
import ra.entity.Category;
import ra.file.FileManager;

import java.util.List;
import java.util.stream.Collectors;
import static ra.run.Library.bookList;
import static ra.run.Library.categoryList;
import static ra.run.Library.scanner;
public class BookMenu {
    public static void bookMenu() {
        do {
            System.out.println("⍡✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧⍡");
            System.out.println("⎜                          "+ColorManager.XANHLAMA+"QUẢN LÝ SÁCH"+ColorManager.RESET+"                          ⎜");
            System.out.println("⎜✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧⎜");
            System.out.println("⎜ "+ColorManager.YELLOW+"➣ 1."+ColorManager.RESET+" ⎜      "+ColorManager.XANHLAMA+"Thêm mới sách"+ColorManager.RESET+"                                      ⎜");
            System.out.println("⎜ "+ColorManager.YELLOW+"➣ 2."+ColorManager.RESET+" ⎜      "+ColorManager.XANHLAMA+"Cập nhật thông tin sách"+ColorManager.RESET+"                            ⎜");
            System.out.println("⎜ "+ColorManager.YELLOW+"➣ 3."+ColorManager.RESET+" ⎜      "+ColorManager.XANHLAMA+"Xóa sách"+ColorManager.RESET+"                                           ⎜");
            System.out.println("⎜ "+ColorManager.YELLOW+"➣ 4."+ColorManager.RESET+" ⎜      "+ColorManager.XANHLAMA+"Tìm kiếm sách"+ColorManager.RESET+"                                      ⎜");
            System.out.println("⎜ "+ColorManager.YELLOW+"➣ 5."+ColorManager.RESET+" ⎜      "+ColorManager.XANHLAMA+"Hiển thị danh sách sách theo nhóm thể loại"+ColorManager.RESET+"         ⎜");
            System.out.println("⎜ "+ColorManager.YELLOW+"➣ 6."+ColorManager.RESET+" ⎜      "+ColorManager.XANHLAMA+"Quay lại"+ColorManager.RESET+"                                           ⎜");
            System.out.println("⍡✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧⍡");
            System.out.print(ColorManager.YELLOW + "  ➣ Chọn : \t   " + ColorManager.RESET);
            try {
                int choiceBook = Integer.parseInt(scanner.nextLine());
                switch (choiceBook){
                    case 1:
                        saveBook();
                        FileManager.writeDataToBookFile(bookList);
                        break;
                    case 2:
                        updateBook();
                        FileManager.writeDataToBookFile(bookList);
                        break;
                    case 3:
                        deleteBook();
                        FileManager.writeDataToBookFile(bookList);
                        break;
                    case 4:
                        searchBook();
                        break;
                    case 5:
                        displayBookByCatalogId();
                        break;
                    case 6:
                        Library.libraryMenu();
                    default:
                        System.out.println(ColorManager.RED+"Vui lòng nhập lại từ 1 đến 6."+ColorManager.RESET);
                }
            } catch (NumberFormatException ex1){
                System.out.println(ColorManager.RED+"Đầu vào phải là kiểu số nguyên. Vui lòng nhập lại."+ColorManager.RESET);
            }
        } while(true);
    }
    // thêm mới sách
    public static void saveBook(){
        if (categoryList.isEmpty()){
            System.out.println(ColorManager.RED+"Không có thể loại nào để thêm mới sách. Vui lòng thêm thể loại."+ColorManager.RESET);
            return;
        }
        System.out.println();
        System.out.println(ColorManager.XANHLAMA+" ✰✰✰✰✰✰✰✰✰✰✰✰✰✰✰✰ Danh sách thể loại ✰✰✰✰✰✰✰✰✰✰✰✰✰✰✰✰"+ColorManager.RESET);
        for (Category category : categoryList) {
            System.out.println(ColorManager.PURPLE+" ● Mã thể loại : " + category.getId() + "\t-\tTên thể loại : " + category.getName()+ColorManager.RESET);
        }
        System.out.println(ColorManager.GREEN+"Nhập số lượng sách cần thêm : "+ColorManager.RESET);
        do {
            try {
                int number = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < number; i++) {
                    System.out.println(ColorManager.XANHLAMA+"✰✰✰✰✰ Nhập vào mã thể loại cho sách thứ " + (i+1) +" ✰✰✰✰✰"+ColorManager.RESET);
                    try {
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        Category selectedCategory = categoryList.stream().filter(category ->
                                category.getId() == categoryId).findFirst().orElse(null);
                        if (selectedCategory == null){
                            System.out.println(ColorManager.RED+"Không tìm thấy thể loại bằng mã thể loại đã nhập. Không thêm sách."+ColorManager.RESET);
                            continue;
                        }
                        Book newBook = new Book();
                        newBook.inputAddnewBook();
                        newBook.setCatalogId(categoryList.get(categoryId-1).getId());
                        bookList.add(newBook);
                        System.out.println();
                        System.out.println(ColorManager.GREEN+"Đã thêm mới sách thứ " +(i+1) +" thành công ✔︎");
                        System.out.println();
                    } catch (NumberFormatException e){
                        System.out.println(ColorManager.RED+"Đầu vào phải là số nguyên. Vui lòng nhập lại."+ColorManager.RESET);
                    }
                }
                break;
            } catch (NumberFormatException e){
                System.out.println(ColorManager.RED+"Số lượng sách phải là số. Vui lòng nhập lại."+ColorManager.RESET);
            } catch (Exception exception){
                System.out.println(ColorManager.RED+"Xảy ra lỗi "+exception.getMessage()+"❗️ Vui lòng liên hệ tới hệ thống."+ColorManager.RESET);
            }
        } while (true);
    }
    // khi thêm 1 sách thì chọn thể loại thêm sách. danh sách catalog bắt đầu index = 0 thì gán getId vị trí index của câtloglist cho catalogId của booklist thì khi gán dung fori -1 đi.
    // cập nhật thông tin sách
    public static void updateBook(){
        if (bookList.isEmpty()){
            System.out.println(ColorManager.RED+"Không có sách nào để cập nhật."+ColorManager.RESET);
            return;
        }
        System.out.println();
        System.out.println(ColorManager.GREEN+"Nhập vào mã sách cần cập nhật :"+ColorManager.RESET);
        String updateBookId = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId().equals(updateBookId)){
                index = i;
                break;
            }
        }
        if (index != -1){
            // cập nhật tiếp thông tin sách
            bookList.get(index).setTitle(Book.validateBookTitle());
            bookList.get(index).setAuthor(Book.validateBookAuthor());
            bookList.get(index).setPublisher(Book.validateBookPusblisher());
            bookList.get(index).setYear(Book.validateBookYear());
            bookList.get(index).setDescription(Book.validateBookDescription());
        } else {
            System.out.println(ColorManager.RED+"Không tồn tại mã sinh viên " + updateBookId +"."+ColorManager.RESET);
        }
        System.out.println();
        System.out.println();
    }
    // xoá sách
    public static void deleteBook() {
        if (bookList.isEmpty()) {
            System.out.println(ColorManager.RED+"Không có sách nào để xoá."+ColorManager.RESET);
            return;
        }
        System.out.println();
        System.out.println(ColorManager.GREEN+"Nhập mã sách cần xoá :"+ColorManager.RESET);
        String deleteBookID = scanner.nextLine();
        Book foundBook = bookList.stream().filter(book -> book.getId().equals(deleteBookID)).findFirst().orElse(null);
        if (foundBook == null) {
            System.out.println(ColorManager.RED+"Không tìm thấy sách đã nhập."+ColorManager.RESET);
        } else {
            bookList.remove(foundBook);
            System.out.println(ColorManager.GREEN+"Xoá sách thành công ✔︎"+ColorManager.RESET);
        }
        System.out.println();
        System.out.println();
    }
    // tìm kiếm sách
    public static void searchBook(){
        System.out.println();
        System.out.println(ColorManager.GREEN+"Nhập vào từ khoá cần tìm :"+ColorManager.RESET);
        String keyword = scanner.nextLine();
        List<Book> searchResult = bookList.stream().filter(book ->
                book.getId().toLowerCase().contains(keyword) ||
                book.getTitle().toLowerCase().contains(keyword) ||
                book.getAuthor().toLowerCase().contains(keyword) ||
                book.getPublisher().toLowerCase().contains(keyword) ||
                book.getDescription().toLowerCase().contains(keyword)).collect(Collectors.toList());
        if (searchResult.isEmpty()){
            System.out.println(ColorManager.RED+"Không tìm thấy thông tin của sách phù hợp với từ khoá cần tìm. Vui lòng nhập lại."+ColorManager.RESET);
        } else{
            System.out.println("Các thông tin của sách phù hợp với từ khoá cần tìm là :");
            System.out.println();
            System.out.printf(ColorManager.PURPLE+" Mã sách       Tiêu đề                                  Tác giả    　      Nhà xuất bản　   Năm      Mô tả sách         Thể loại       \n"+ColorManager.RESET);
            for (Book book: searchResult) {
                String categoryName = categoryList.stream().filter(category -> category.getId() == book.getCatalogId()).collect(Collectors.toList()).get(0).getName();
                String formattedBookId = book.getId().replaceAll(keyword.toLowerCase(),ColorManager.GREEN + keyword.toLowerCase() + ColorManager.RESET);
                String formattedTitle = book.getTitle().replaceAll(keyword.toLowerCase(),ColorManager.GREEN + keyword.toLowerCase() + ColorManager.RESET);
                String formattedAuthor = book.getAuthor().replaceAll(keyword.toLowerCase(),ColorManager.GREEN + keyword.toLowerCase() + ColorManager.RESET);
                String formattedDescription = book.getDescription().replaceAll(keyword.toLowerCase(),ColorManager.GREEN + keyword.toLowerCase() + ColorManager.RESET);
                String formattedPublisher = book.getPublisher().replaceAll(keyword.toLowerCase(),ColorManager.GREEN + keyword.toLowerCase() + ColorManager.RESET);
                System.out.printf(" %-10s   \t\t%-38s   %-16s   %-14s   %-5d   %-16s   %-16s  \n",formattedBookId,formattedTitle,formattedAuthor,formattedPublisher,book.getYear(),formattedDescription,categoryName);
//                    book.output();
            }
//            searchResult.forEach(Book::output);
        }
        System.out.println();
        System.out.println();
    }
    // hiển thị danh sách sách theo nhóm thể loại
    public static void displayBookByCatalogId(){
        // kiểm tra xem list sách vs thể loại có tòn tại hay không
        if (categoryList.isEmpty() || bookList.isEmpty()){
            System.out.println(ColorManager.RED+"Không có thể loại hoặc sách để hiển thị."+ColorManager.RESET);
            return;
        }
        System.out.println();
        System.out.println(ColorManager.XANHLAMA+"✯✯✯✯✯✯✯✯✯✯✯✯✯✯ Danh sách sách theo nhóm thể loại ✯✯✯✯✯✯✯✯✯✯✯✯✯✯"+ColorManager.RESET);
        System.out.println();
        // Duyêt các thể loại
        categoryList.stream().forEach(category -> {
            System.out.println("\t" +ColorManager.XANHLAMA+ category.getName() +" :"+ColorManager.RESET);
            // In thông tin thể loai;
             // in thông tin các sách thuộc thể loại đang xét
            // cách dùng java8
//            bookList.stream().filter(book -> book.getCatalogId() == category.getId()).forEach(book ->
//                    book.output());
            // cách dùng thường
            for (Book book: bookList) {
                if (book.getCatalogId() == category.getId()){
                    book.outPutOfCatalogNameAndBookName();
                }
            }
        });
//        bookList.forEach(book -> book.output());
        System.out.println();
    }
}
