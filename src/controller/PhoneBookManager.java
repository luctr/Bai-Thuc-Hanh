package controller;

import model.PhoneBook;
import storage.FileManager;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookManager {
    private static PhoneBookManager INSTANCE = new PhoneBookManager();
    private List<PhoneBook> phoneBooks = new ArrayList<>();

    public List<PhoneBook> getPhoneBooks() {
        return phoneBooks;
    }

    public void setPhoneBooks(List<PhoneBook> phoneBooks) {
        this.phoneBooks = phoneBooks;
    }

    private PhoneBookManager() {
    }

    public static PhoneBookManager getINSTANCE() {
        return INSTANCE;
    }

    public void addList(PhoneBook phoneBook) {
        this.phoneBooks.add(phoneBook);

    }

    public void showPhoneBook() {
        if (phoneBooks.size() > 0)
            for (int i = 0; i < phoneBooks.size(); i++) {
                System.out.println(phoneBooks.get(i));
            }
        else {
            System.out.println("Không có phần tử trong danh bạ");
        }
    }

    public void deletePhoneBook(String phone) {
        phoneBooks.remove(searchPhoneBook(phone));
        System.out.println("Xóa Thành Công");
    }

    public PhoneBook checkNumber(String number) {
        for (int i = 0; i < phoneBooks.size(); i++) {
            if (phoneBooks.get(i).getPhoneNumber().equals(number)){
                return phoneBooks.get(i);
            }
        }
       return null;
    }
    public PhoneBook searchPhoneBook(String number) {
        for (int i = 0; i < phoneBooks.size(); i++) {
            if (phoneBooks.get(i).getPhoneNumber().equals(number)) {
                return phoneBooks.get(i);
            }
        }
        return null;
    }
    public void searchName(String Name){
        for (int i = 0; i < phoneBooks.size(); i++) {
            if (phoneBooks.get(i).getName().equalsIgnoreCase(Name)){
                System.out.println(phoneBooks.get(i));
            }else {
                System.out.println("Không Có Tên Trong Danh Sách");
            }
        }
    }
    public void writeFile(){
        FileManager.writeFile("data/contacts.csv",phoneBooks);
    }


}
