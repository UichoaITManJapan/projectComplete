package ra.entity;

import ra.IEntity;
import ra.color.ColorManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.stream.Collectors;

import static ra.run.Library.*;


public class Book implements IEntity, Serializable {
    private String id; // mã sách
    private String title; // tiều đề sách
    private String author; // tác giả
    private String publisher; // nhà sản xuất
    private int year; // năm xuất bản
    private String description; // Mô tả sách
    private int catalogId; // mã thể loại sách


    public Book() {
    }

    public Book(String id, String title, String author, String publisher, int year, String description, int catalogId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.catalogId = catalogId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public void input() {
        // Validate Book
        this.id = validateBookId();
        this.title = validateBookTitle();
        this.author = validateBookAuthor();
        this.publisher = validateBookPusblisher();
        this.year = validateBookYear();
        this.description = validateBookDescription();
        this.catalogId = validateCategoryId();
    }
    public void inputAddnewBook(){
        this.id = validateBookId();
        this.title = validateBookTitle();
        this.author = validateBookAuthor();
        this.publisher = validateBookPusblisher();
        this.year = validateBookYear();
        this.description = validateBookDescription();
    }

    //Validate bookId
    public String validateBookId() {
        System.out.println(ColorManager.XANHLAMA +"Nhập vào mã sách :"+ColorManager.RESET);
        do {
            String inputBookId = scanner.nextLine();
            if (inputBookId != null || inputBookId.trim().length() != 0) {
                try {
                    String bookId = inputBookId;
                    if (bookId.startsWith("B") && bookId.length() == 4) {
                        boolean isExistBookId = false;
                        for (Book book : bookList) {
                            if (book.getId().equals(bookId)) {
                                isExistBookId = true;
                                break;
                            }
                        }
                        if (isExistBookId) {
                            System.out.println(ColorManager.RED+"Mã sách đã tồn tại. Vui lòng nhập lại!"+ColorManager.RESET);
                        } else {
                            return bookId;
                        }
                    } else {
                        System.out.println(ColorManager.RED+"Mã sách phải bắt đầu là 'B' và phải có 4 ký tự. Vui lòng nhập lại."+ColorManager.RESET);
                    }
                } catch (Exception e) {
                    System.out.println(ColorManager.RED+"Xảy ra lỗi"+e.getMessage()+"❗️ Vui lòng liên hệ tới hệ thống."+ColorManager.RESET);
                }
            } else {
                System.out.println(ColorManager.RED+"Mã sách không được để trống. Vui lòng nhập mã sách."+ColorManager.RESET);
            }
        } while (true);
    }

    // Validate bookTitle
    public static String validateBookTitle() {
        System.out.println(ColorManager.XANHLAMA+"Nhập vào tiêu đề sách :"+ColorManager.RESET);
        do {
            String inputBookTitle = scanner.nextLine();
            if (inputBookTitle != null || inputBookTitle.trim().length() != 0) {
                try {
                    String bookTitle = inputBookTitle;
                    boolean existBookTitle = false;
                    for (Book book : bookList) {
                        if (book.getTitle().equals(bookTitle)) {
                            existBookTitle = true;
                            break;
                        }
                    }
                    if (existBookTitle) {
                        System.out.println(ColorManager.RED+"Tiêu đề sách đã tồn tại. Vui lòng nhập lại."+ColorManager.RESET);
                    } else {
                        if (bookTitle.length() >= 6 && bookTitle.length() <= 50) {
                            return bookTitle;
                        } else {
                            System.out.println(ColorManager.RED+"Tiêu đề sách phải từ 6 đến 50 ký tự. Vui lòng nhập lại."+ColorManager.RESET);
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ColorManager.RED+"Xảy ra lỗi"+ex.getMessage()+"❗️ Vui lòng nhập lại."+ColorManager.RESET);
                }
            } else {
                System.out.println(ColorManager.RED+"Tiêu đề sách không được để trống. Vui lòng nhập tiêu đề sách."+ColorManager.RESET);
            }
        } while (true);
    }

    public static String validateBookAuthor() {
        System.out.println(ColorManager.XANHLAMA+"Nhập vào tên tác giả :"+ColorManager.RESET);
        do {
            String inputBookAuthor = scanner.nextLine().trim();
            if (!inputBookAuthor.isEmpty()) {
                return inputBookAuthor;
            } else {
                System.out.println(ColorManager.RED+"Tên tác giả không được để trống. Vui lòng nhập vào tên tác giả."+ColorManager.RESET);
            }
        } while (true);
    }

    // validate bookPublisher
    public static String validateBookPusblisher() {
        System.out.println(ColorManager.XANHLAMA+"Nhập vào tên nhà xuất bản :"+ColorManager.RESET);
        do {
            String inputBookPublisher = scanner.nextLine().trim();
            if (!inputBookPublisher.isEmpty()) {
                return inputBookPublisher;
            } else {
                System.out.println(ColorManager.XANHLAMA+"Nhà xuất bản không được để trống. Vui lòng nhập vào tên nhà xuất bản."+ColorManager.RESET);
            }
        } while (true);
    }

    public static int validateBookYear() {
        System.out.println(ColorManager.XANHLAMA+"Nhập vào năm xuất bản :"+ColorManager.RESET);
        do {
            String inputBookYear = scanner.nextLine();
            if (inputBookYear != null || inputBookYear.trim().length() != 0) {
                try {
                    int bookYear = Integer.parseInt(inputBookYear);
                    LocalDate currentDate = LocalDate.now();
                    int currentYear = currentDate.getYear();
                    if (bookYear >= 1970 && bookYear <= currentYear) {
                        return bookYear;
                    } else {
                        System.out.println(ColorManager.RED+"Năm xuất bản phải tối thiểu từ năm 1970 và không lớn hơn năm " + currentYear + ". Vui lòng nhập lại."+ColorManager.RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ColorManager.RED+"Năm xuất bản phải là số nguyên. Vui lòng nhập lại."+ColorManager.RESET);
                }
            } else {
                System.out.println(ColorManager.RED+"Năm xuất bản không được để trống. Vui lòng nhập vào năm xuất bản."+ColorManager.RESET);
            }
        } while (true);
    }

    // validate bookDescription
    public static String validateBookDescription() {
        System.out.println(ColorManager.XANHLAMA+"Nhập vào mô tả sách :"+ColorManager.RESET);
        do {
            String inputBookDes = scanner.nextLine().trim();
            if (!inputBookDes.isEmpty()) {
                return inputBookDes;
            } else {
                System.out.println(ColorManager.RED+"Mô tả sách không được bỏ trống. Vui lòng nhập mô tả sách."+ColorManager.RESET);
            }
        } while (true);
    }
    public static int validateCategoryId(){
        System.out.println(ColorManager.XANHLAMA+"Nhập vào mã thể loại sách :"+ColorManager.RESET);
        do {
            String inputCategoryId = scanner.nextLine();
            if (inputCategoryId != null || inputCategoryId.length() != 0){
                try {
                    int categoryId = Integer.parseInt(inputCategoryId);
//                    khi thêm 1 sách thì chọn thể loại thêm sách. danh sách catalog bắt đầu index = 0 thì gán getId vị trí index của câtloglist cho catalogId của booklist thì khi gán dung fori -1 đi.
                    for (Category cate: categoryList) {
                       categoryId = cate.getId();
                    }
                    return categoryId;
                } catch (NumberFormatException e){
                    System.out.println(ColorManager.RED+"Lỗi " + e.getMessage()+"❗️ " + "Vui lòng nhập lại."+ColorManager.RESET);
                }
            } else {
                System.out.println(ColorManager.XANHLAMA+"Mã thể loại không được để trống. Vui lòng nhập vào mã thể loại."+ColorManager.RESET);
            }
        } while (true);
    }

    @Override
    public void output() {
        String categoryName = categoryList.stream().filter(category -> category.getId() == this.catalogId).collect(Collectors.toList()).get(0).getName();
//        System.out.format("+------------+----------------------------------------+------------------+----------------+-------+------------------+------------------+");
//        System.out.printf(" Mã sách       Tiêu đề                                  Tác giả    　      Nhà xuất bản　   Năm      Mô tả sách         Thể loại       \n");
//        System.out.format("+------------+----------------------------------------+------------------+----------------+-------+------------------+------------------+");
        System.out.printf(ColorManager.XANHLAMA+"  %-10s   %-38s   %-16s   %-14s   %-5d   %-16s   %-16s  \n"+ColorManager.RESET,
                id,title,author,publisher,year,description,categoryName);
        System.out.println();
//        System.out.format("+------------+----------------------------------------+------------------+----------------+-------+------------------+------------------+");
    }
    public void outPutOfCatalogNameAndBookName(){
        System.out.printf(ColorManager.PURPLE+" %-15s  %-10s\n"+ColorManager.RESET," ",title);
    }
    }

