package com.NamTQ.Models;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class Customer extends User{
    private  List<Account> accounts;
    public Customer(String name, String customerID, List<Account> accounts) {
        super(name, customerID);
        this.accounts = new ArrayList<>(accounts);
    }
    public List<Account> getAccounts() {
        return accounts;
    }
//    xác định khách hàng có là Premium không
    public boolean isPremium(){
        for (Account account:accounts){
            if(account.isPremium())
                return true;
        }
        return false;
    }
    public void addAccount(Account newAccount){
        accounts.add(newAccount);
    }
//    tính tổng số dư
    public double getBalance(){
        double sum = 0;
        for(Account account: accounts){
            sum +=account.getBalance();
        }
        return sum;
    }
//    hiển thị thông tin
    public void displayInformation() {
        String isPre;
        String vn = NumberFormat.getCurrencyInstance(new Locale("VN","VN")).format(getBalance());
        if(isPremium())
                isPre="Premium";
            else
                isPre="Normal";
        System.out.printf("|%-10s | %-15s | %-7s |%-15s %n", getCustomerID(), getName(), isPre,vn);
        for(int i = 0; i < this.accounts.size() ; i++){
            System.out.println(i+1 + "  " + this.accounts.get(i).toString());
         }
    }
}
