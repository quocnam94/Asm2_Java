package com.NamTQ.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Customer extends User{
    private  List<Account> accounts;

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
    public boolean isAccountExisted(String accountNumber){
        for(Account account: accounts){
                if (Objects.equals(accountNumber, account.getAccountNumber()))
                return true;
        }
        return false;
    }
    public double getBalance(){
        double sum = 0;
        for(Account account: accounts){
            sum +=account.getBalance();
        }
        return sum;
    }
    public void displayInformation(){
        String isPre;
        if(isPremium())
            isPre = "Premium";
        else
            isPre = "Normal";
        System.out.println(getCustomerID() + "  " + getName() + "  " + isPre + " " + getBalance());
        for (int i = 0; i<=this.accounts.size(); i++){
            System.out.println(i+1 + "  " + this.accounts.get(i).toString());
        }
    }
}
