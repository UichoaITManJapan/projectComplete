package ra.entity;

import ra.IEntity;
import ra.color.ColorManager;

import java.io.Serializable;

import static ra.run.Library.categoryList;
import static ra.run.Library.scanner;

// Đây là lớp thể loại sách
public class Category implements IEntity, Serializable {
    private int id;
    private String name;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void input() {
        this.id = validateCatalogId();
        this.name = validateCatalogName();
        this.status = validateCatalogStatus();
    }
    //Validate CatalogID
    public int validateCatalogId(){
        System.out.println(ColorManager.TIM+"Nhập vào mã thể loại :"+ColorManager.RESET);
        do {
            // Mã thư mục không được để trống
            String inputStringCatalogId = scanner.nextLine();
            if (inputStringCatalogId != null || inputStringCatalogId.trim().length() != 0){
                // kiểm tra tiếp catalogID
                try {
                    int catalogId = Integer.parseInt(inputStringCatalogId);
                    if (catalogId > 0){
                           boolean isExistCatalogId = false;
                        for (Category ct: categoryList) {
                            if (ct.getId() == catalogId){
                                isExistCatalogId = true;
                            }
                        }
                        if (!isExistCatalogId){
                            return catalogId;
                        } else {
                            System.out.println(ColorManager.RED+"Mã thể loại đã tồn tại. Vui lòng nhập lại!"+ColorManager.RESET);
                        }
                    } else {
                        System.out.println(ColorManager.RED+"Mã thể loại phải là số nguyên lớn hơn 0. Vui lòng nhập lại!"+ColorManager.RESET);
                    }
                } catch (NumberFormatException ex1){
                    System.out.println(ColorManager.RED+"Lỗi " + ex1.getMessage()+". Vui lòng nhập lại!"+ColorManager.RESET);
                }
            } else {
                System.out.println(ColorManager.RED+"Mã thể loại không được để trồng. Vui lòng nhập mã thể loại!"+ColorManager.RESET);
            }
        } while (true);


    }
    // Validate CatalogName
    public static String validateCatalogName(){
        System.out.println(ColorManager.TIM+"Nhập vào tên thể loại :"+ColorManager.RESET);
        do {
            // tên thể loại không được để trống
            String inputStringCatalogName = scanner.nextLine();
            if (inputStringCatalogName != null || inputStringCatalogName.trim().length() != 0){
                try {
                    String catalogName = inputStringCatalogName;
                    boolean isExistCatalogName = false;
                    for (Category ct: categoryList) {
                        if (ct.getName().length() >= 6 && ct.getName().length()<= 30){
                            if (ct.getName().equals(catalogName)){
                                isExistCatalogName = true;
                            }
                        } else {
                            System.out.println(ColorManager.TIM+"Tên thể loại phải có từ 6-30 ký tự. Vui lòng nhập lại!"+ColorManager.RESET);
                        }
                    }
                    if (!isExistCatalogName){
                        return catalogName;
                    } else {
                        System.out.println(ColorManager.RED+"Tên thể loại đã tồn tại. VUi lòng nhập lại!"+ColorManager.RESET);
                    }
                } catch (Exception e){
                    System.out.println(ColorManager.RED+"Xảy ra lỗi. Vui lòng liên hệ tới hệ thống!"+ColorManager.RESET);
                }
            } else {
                System.out.println(ColorManager.RED+"Tên thể loại không được để trống. Vui lòng nhập tên thể loại!"+ColorManager.RESET);
            }
        }while (true);
    }
    public static boolean validateCatalogStatus(){
        System.out.println(ColorManager.TIM+"Nhập vào trạng thái thể loại :"+ColorManager.RESET);
        do {
            String inputStatus = scanner.nextLine().trim().toLowerCase();
            if (!inputStatus.isEmpty()){
                if (inputStatus.equals("true")||inputStatus.equals("false")){
                    return Boolean.parseBoolean(inputStatus);
                } else {
                    System.out.println(ColorManager.RED+"Đầu vào bị lỗi. Vui lòng nhập trạng thái thể loại là 'true' hoặc 'false'!"+ColorManager.RESET);
                }
            } else {
                System.out.println(ColorManager.RED+"Trạng thái thể loại không được để trống. Vui lòng nhập trạng thái thể loại!"+ColorManager.RESET);
            }
        } while (true);
    }

    @Override
    public void output() {
        String checkStatus = this.status ? "Hoạt động" : "Không hoạt động";
//        DecimalFormat ft = new DecimalFormat("0000");
        System.out.printf(ColorManager.CYAN+" %-6d  %-18s  %-18s \n"+ColorManager.RESET,id,name,checkStatus);

    }


}
