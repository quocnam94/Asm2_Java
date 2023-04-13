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

    public static void main(String[] args) {
        menu();
        select();
        addCustomers();
        addAccount();
    }

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
                    default:
                        System.out.println("Vui long nhap so tu 0 toi 5");
                }
            }
        } catch (Exception InputMismatchException) {
            System.out.println("Vui long nhap so");
            select();
        }
    }

    public static void addCustomers() {
        Scanner sc = new Scanner(System.in);
        String name, customerID;
        System.out.print("Nhap ten khach hang: ");
        name = sc.next();
        System.out.print("Nhap so CCCD: ");
        customerID = sc.next();
        while (customerID.length() != 12 || !customerID.matches("\\d+")) {
            System.out.println("So CCCD khong hop le. Vui long nhap lai hoac 'No' de thoat");
            customerID = sc.next();
            if (customerID.equals("No")) {
                System.exit(0);
            }
        }
        boolean isExited = bank.isCustomerExisted(customerID);
        while (isExited) {
            System.out.println("So CCCD bi trung. Vui long nhap so CCCD khac");
            customerID = sc.next();
            isExited = bank.isCustomerExisted(customerID);
        }
        Customer customer = new Customer(name, customerID,new ArrayList<>());
        bank.addCustomer(customer);
        System.out.println("Da them khach hang " + customerID + " vao danh sach");
        System.out.print("Chuc nang: ");
    }
    public static void addAccount() {
        Scanner sc = new Scanner(System.in);
        String customerId, accountNumber;
        double balance;
        System.out.println("Nhap CCCD khach hang: ");
        customerId = sc.next();
        boolean isExisted = bank.isCustomerExisted(customerId);
        while (!isExisted) {
            System.out.println("So CCCD khong ton tai");
            addAccount();
            isExisted = bank.isCustomerExisted(customerId);
            customerId = sc.next();
            break;
        }
        System.out.println("Nhap ma STK gom 6 chu so: ");
        accountNumber = sc.next();
        while (accountNumber.length() != 6 || !accountNumber.matches("\\d+")) {
            System.out.println("STK khong hop le. Vui long nhap lai STK.");
            accountNumber = sc.next();
        }
        System.out.println("Nhap so du: ");
        balance = sc.nextDouble();
        while (balance < 50000) {
            System.out.println("So du toi thieu la 50000. Vui long nhap lai");
            balance = sc.nextDouble();
        }
        Account account = new Account(customerId, accountNumber, balance);
        for (int i = 0; i < bank.getCustomers().size(); i++) {
            boolean isExistedAccount = bank.getCustomers().get(i).isAccountExisted(accountNumber);
            while (isExistedAccount) {
                System.out.println("STK bi trung. Vui long nhap lai STK.");
                isExistedAccount = bank.getCustomers().get(i).isAccountExisted(accountNumber);
                accountNumber = sc.next();
                break;
            }
            bank.getCustomers().get(i).addAccount(account);
        }
        System.out.println("Them tai khoan thanh cong");
        System.out.print("Chuc nang: ");
    }

    public static void showCustomerList() {
        for (int i = 0; i < bank.getCustomers().size(); i++) {
            bank.getCustomers().get(i).displayInformation();
        }
    }
}






























