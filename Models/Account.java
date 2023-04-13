package com.NamTQ.Models;

public class Account {
    private String customerId;
    private String accountNumber;
    private double balance;
    public Account(String customerId, String accountNumber, double balance) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public boolean isPremium(){
        return getBalance()>=10000000;
    }
    public String toString(){
       return " " +  accountNumber  + "  |                          " + balance;
    }
}
