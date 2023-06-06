package com.NamTQ;

import com.NamTQ.Models.Account;
import com.NamTQ.Models.Bank;
import com.NamTQ.Models.Customer;
import com.NamTQ.Models.User;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.*;

public class Asm2 {
    private static final Bank bank = new Bank();
//hàm main
    public static void main(String[] args) {
        menu();
        select();
        addCustomers();
        addAccount();
        findCustomerId();
        findCustomerName();
    }
//menu chương trình
    public static void menu() {
        String AUTHOR = "FX20225";
        String VERSION = "V2.0.0";
        System.out.println("+----------+--------------------+----------+");
        System.out.println("|  NGAN HANG SO  |  " + AUTHOR + "@" + VERSION + "         |");
        System.out.println("+----------+--------------------+----------+");
        System.out.println("   1. Them khach hang ");
        System.out.println("   2. Them tai khoan cho khach hang ");
        System.out.println("   3. Hien thi danh sach khach hang ");
        System.out.println("   4. Tim theo CCCD ");
        System.out.println("   5. Tim theo ten khach hang ");
        System.out.println("   0. Thoat ");
        System.out.println("+----------+--------------------+----------+");
        System.out.print("Chuc nang: ");
    }
//lựa chọn các option
    public static void select() {
        Scanner sc = new Scanner(System.in);
        int n;
        try {
            while (true) {
                n = sc.nextInt();
                switch (n) {
                    case 0:
                        System.out.println("Chuong trinh ket thuc !!!");
                        System.exit(0);
                    case 1:
                        addCustomers();
                        break;
                    case 2:
                        addAccount();
                        break;
                    case 3:
                        showCustomerList();
                        break;
                    case 4:
                        findCustomerId();
                        break;
                    case 5:
                        findCustomerName();
                        break;
                    default:
                        System.out.println("Vui long nhap so tu 0 toi 5");
                }
            }
        }
        catch (Exception InputMismatchException) {
            System.out.println("Vui long nhap so tu 0 toi 5");
            select();
        }
    }
//thêm khách hàng
    public static void addCustomers() {
        Scanner sc = new Scanner(System.in);
        String customerName, customerID;
        System.out.print("Nhap ten khach hang: ");
        customerName = sc.nextLine();
        System.out.print("Nhap so CCCD: ");
        customerID = sc.next();
        while (!User.validCustomerId(customerID)) {
            System.out.println("So CCCD khong hop le. Vui long nhap lai so CCCD hoac 'No' de thoat");
            customerID = sc.next();
            if (customerID.equals("No") ||customerID.equals("no") ) {
                System.out.println("Chuong trinh ket thuc !!!");
                System.exit(0);
            }
        }
        boolean isExited = bank.isCustomerExisted(customerID);
        while (!isExited) {
            System.out.println("So CCCD bi trung. Vui long nhap so CCCD khac");
            customerID = sc.next();
            isExited = bank.isCustomerExisted(customerID);
        }
        Customer customer = new Customer(customerName, customerID, new ArrayList<>());
        bank.addCustomer(customer);
        System.out.println("Da them khach hang " + customerID + " vao danh sach");
        System.out.print("Chuc nang: ");
    }
//thêm tài khoản
    public static void addAccount() {
        Scanner sc = new Scanner(System.in);
        String customerId, accountNumber;
        double balance;
//        kiểm tra số CCCD
        System.out.println("Nhap CCCD khach hang: ");
        customerId = sc.next();
        boolean isExisted = bank.isCustomerExisted(customerId);
        while (!isExisted) {
            System.out.println("So CCCD khong ton tai. Vui long nhap so CCCD khac");
            isExisted = bank.isCustomerExisted(customerId);
            customerId = sc.next();
            break;
        }
//        thêm STK
        System.out.println("Nhap STK gom 6 chu so: ");
        accountNumber = sc.next();
        while (accountNumber.length() != 6 || !accountNumber.matches("\\d+")) {
            System.out.println("STK khong hop le. Vui long nhap lai STK.");
            accountNumber = sc.next();
        }
//        thêm số dư
        System.out.println("Nhap so du: ");
        while (true) {
            try {
                balance = sc.nextDouble();
                if (balance < 50000) {
                    System.out.println("So du toi thieu la 50000. Vui long nhap lai");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Vui long nhap so");
                sc.next();
            }
        }
//        kiểm tra STK bị trùng
        boolean isDuplicate = false;
        boolean isExistedAccount = bank.isAccountExisted(accountNumber);
        while (isExistedAccount) {
            System.out.println("STK bi trung. Vui long nhap STK khac");
            accountNumber = sc.next();
            System.out.println("Nhap so du: ");
            balance = sc.nextDouble();
            while (balance < 50000) {
                System.out.println("So du toi thieu la 50000. Vui long nhap lai");
                balance = sc.nextDouble();
            }
            isExistedAccount = bank.isAccountExisted(accountNumber);
        }
        if (!isDuplicate) {
            Account account = new Account(customerId, accountNumber, balance);
            for (int i = 0; i < bank.getCustomers().size(); i++) {
                if (Objects.equals(bank.getCustomers().get(i).getCustomerID(), customerId)) {
                    bank.addAccount(customerId,new Account(customerId, accountNumber, balance));
                    break;
                }
            }
        }
        System.out.println("Them tai khoan thanh cong");
        System.out.print("Chuc nang: ");
    }
//    hiện danh sách khách hàng
    public static void showCustomerList() {
        for (int i = 0; i < bank.getCustomers().size(); i++) {
            bank.getCustomers().get(i).displayInformation();
        }
        System.out.print("Chuc nang: ");
    }
//    tìm theo số CCCD
    public static void findCustomerId(){
        ArrayList<Customer>customers=bank.getCustomers();
        System.out.print("Nhap so CCCD can tim: ");
        Scanner sc = new Scanner(System.in);
        String customerId = sc.nextLine();
        boolean found = false;
        for(int i =0; i<customers.size(); i++) {
            if (customers.get(i).getCustomerID().equals(customerId)) {
                customers.get(i).displayInformation();
                System.out.print("Chuc nang: ");
                found = true;
                break;
            }
        }
        if(!found) {
                System.out.println("Khach hang chua co trong he thong.");
                System.out.print("Chuc nang: ");
        }
    }
//    tìm theo tên khách hàng
    public static void findCustomerName(){
        ArrayList<Customer>customers=bank.getCustomers();
        System.out.print("Nhap ten khach hang can tim: ");
        Scanner sc = new Scanner(System.in);
        String customerName = sc.nextLine();
        boolean found = false;
        for(int i =0;i<customers.size();i++) {
            if(customers.get(i).getName().contains(customerName)){
                customers.get(i).displayInformation();
                System.out.print("Chuc nang: ");
                found = true;
                break;
            }
        }
        if(!found) {
                System.out.println("Khach hang chua co trong he thong.");
                System.out.print("Chuc nang: ");
        }
    }
}

































