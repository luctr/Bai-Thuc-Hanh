package view;

import controller.PhoneBookManager;
import model.PhoneBook;
import storage.FileManager;

import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        PhoneBookManager phoneBookManager = PhoneBookManager.getINSTANCE();
        do {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----");
            System.out.println(" Chọn Chức Năng Theo Số ( để tiếp tục )");
            System.out.println("1. Xem Danh Sách");
            System.out.println("2. Thêm Mới");
            System.out.println("3. Cập Nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm Kiếm");
            System.out.println("6. Đọc Từ File");
            System.out.println("7. Ghi Vào File");
            System.out.println("8. Thoát");
            System.out.println("Chọn Chức Năng :");
            int choise = new Scanner(System.in).nextInt();
            switch (choise) {
                case 1:
                    phoneBookManager.showPhoneBook();
                    break;
                case 2:
                    phoneBookManager.addList((PhoneBook) createPhoneBook(phoneBookManager));
                    System.out.println("Thêm Thành Công");
                    break;
                case 3:
                    editPhoneBook(phoneBookManager);
                    break;
                case 4:
                    System.out.println("Xóa Theo Số Điện Thoại");
                    phoneBookManager.deletePhoneBook(inputString());
                    break;
                case 5:
                    searchPhoneBook(phoneBookManager);
                    break;
                case 6:
                    List<PhoneBook> phoneBooks = (List<PhoneBook>) FileManager.readFile("data/contacts.csv");
                    break;
                case 7:phoneBookManager.writeFile();
                    break;
                case 8:
                    break;
            }
        } while (true);
    }

    private static void searchPhoneBook(PhoneBookManager phoneBookManager) {
        System.out.println("-----------------");
        System.out.println("1. Tìm Theo Tên");
        System.out.println("2. Tìm Theo Số Điện Thoại");
        int choise = new Scanner(System.in).nextInt();
        switch (choise){
            case 1:
                System.out.println("Nhập Tên Cần Tìm");
                phoneBookManager.searchName(inputString());
                break;
            case 2:
                System.out.println("Nhập Số Điện Thoại Cần Tìm");
                phoneBookManager.searchPhoneBook(inputString());
                break;
        }
    }

    private static void editPhoneBook(PhoneBookManager phoneBookManager) {
        PhoneBook phoneBook = phoneBookManager.searchPhoneBook(inputString());
        System.out.println(phoneBook);
        System.out.println("--------");
        System.out.println("1. Sửa Tên");
        System.out.println("2. Sửa Nhóm");
        System.out.println("3. Sửa Số Điện Thoại");
        System.out.println("4. Sửa Giới Tính");
        System.out.println("5. Sửa Địa Chỉ");
        System.out.println("6. Sửa Email");
        System.out.println("7. Quay Lại");
        int choise = new Scanner(System.in).nextInt();
        switch (choise) {
            case 1:
                phoneBook.setName(inputString());
                break;
            case 2:
                phoneBook.setContactGroup(inputString());
                break;
            case 3:
                phoneBook.setPhoneNumber(testFormatNumber());
                break;
            case 4:
                phoneBook.setGender(testFormatGender());
                break;
            case 5:
                phoneBook.setAddress(inputString());
                break;
            case 6:
                phoneBook.setEmail(testFormatEmail());
                break;
            case 7:
                break;
        }
    }

    private static PhoneBook createPhoneBook(PhoneBookManager phoneBookManager) {
        System.out.println("Thêm Mới");
        System.out.println("Nhập Tên");
        String name = inputString();
        System.out.println("Nhập Nhóm");
        String contactGroup = inputString();
        System.out.println("Nhập Số Điện Thoại");

        String phoneNumber = null;
        while (true) {
            phoneNumber = inputString();
            PhoneBook phoneBook = phoneBookManager.checkNumber(phoneNumber);
            if (phoneBook == null) {
                break;
            } else{
                System.out.println("Số Điện Thoại Không Được Trùng Vui Lòng Nhập LẠI");
            }
        }
        System.out.println("Nhập giới tính");
        String gender = testFormatGender();
        System.out.println("Nhập Địa Chỉ");
        String address = inputString();
        System.out.println("Nhập email");
        String email = testFormatEmail();
        return new PhoneBook(name, contactGroup, phoneNumber, gender, address, email);
    }

    private static String inputString() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String response = scanner.nextLine();
            if (response.length() > 0) {
                return response;
            } else {
                System.out.println("Vui Lòng Nhập Lại");
            }
        }

    }

    private static String testFormatNumber() {
        String phoneNumber;
        while (true) {
            phoneNumber = inputString();
            String pattern = "^0[0-9]{9,10}$";
            boolean matcher = phoneNumber.matches(pattern);
            if (matcher) {
                break;
            } else {
                System.out.println("Định Dạng Nhập Không Đúng " + "Vui Lòng Nhập Lại");
            }

        }
        return phoneNumber;
    }

    private static String testFormatEmail() {
        String email;
        while (true) {
            email = inputString();
            String pattern = "^[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-zA-Z0-9]*$";
            boolean matcher = email.matches(pattern);
            if (matcher) {
                break;
            } else {
                System.out.println("Định Dạng Nhập Không Đúng " + "Vui Lòng Nhập Lại");
            }

        }
        return email;
    }

    private static String testFormatGender() {
        String gender;
        String maleGender = "Nam";
        String femaleGender = "Nu";
        while (true) {
            gender = inputString();
            if (gender.equalsIgnoreCase(maleGender)) {
                return maleGender;
            } else if (gender.equalsIgnoreCase(femaleGender)) {
                return femaleGender;
            } else {
                System.out.println("Giới Tính Không Đúng, Vui Lòng Nhập Lại");
            }
        }
    }
}
