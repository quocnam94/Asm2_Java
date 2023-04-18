package com.NamTQ.Models;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

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
//    xác định tài khoản có là Premium không
    public boolean isPremium(){
        return getBalance()>=10000000;
    }
//hiển thị STK + số dư
    public String toString(){
        String balanceFormatted = NumberFormat.getCurrencyInstance(new Locale("VN","VN")).format(balance);
        return "    " +  accountNumber  + " |                            " + balanceFormatted;
    }
}
