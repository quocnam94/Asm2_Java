package com.NamTQ.Models;

import java.text.NumberFormat;
import java.util.*;

public class Customer extends User{
    private  List<Account> accounts;
    private  ArrayList<Customer> customers;


    public Customer(String name, String customerID, List<Account> accounts) {
        super(name, customerID);
        this.accounts = new ArrayList<>(accounts);
    }
    public List<Account> getAccounts() {
        return accounts;
    }
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
    public double getBalance(){
        double sum = 0;
        for(Account account: accounts){
            sum +=account.getBalance();
        }
        return sum;
    }
    public void displayInformation() {
        String isPre;
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String vn = currencyVN.format(getBalance());
        if(isPremium())
                isPre="Premium";
            else
                isPre="Normal";
        System.out.printf("|%-10s | %-15s | %-7s |%-10s %n", getCustomerID(), getName(), isPre,vn );
        for(int i = 0; i < this.accounts.size() ; i++){
            System.out.println(i+1 + "  " + this.accounts.get(i).toString());
         }
    }

}
