package com.NamTQ.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Bank{
    private  String id;
    private  ArrayList<Customer> customers;
    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
        this.customers = new ArrayList<>();
    }
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
//thêm khách hàng
    public void addCustomer(Customer newCustomer){
        customers.add(newCustomer);
    }
//    thêm tài khoản
    public void addAccount(String customerId, Account newAccount) {
        for (int i = 0; i < customers.size(); i++) {
            if (Objects.equals(customerId, customers.get(i).getCustomerID())) {
                customers.get(i).addAccount(newAccount);
            }
        }
    }
//tìm khách hàng trùng số CCCD
    public boolean isCustomerExisted(String customerId){
        for (int i =0; i<customers.size(); i++){
            if(Objects.equals(customerId,customers.get(i).getCustomerID())){
                return true;
            }
        }
        return false;
    }
//    tìm tài khoản trùng STK
        public boolean isAccountExisted(String accountNumber){
        for (int i =0; i<customers.size(); i++){
            Customer customer = customers.get(i);
            for(int j=0;j<customer.getAccounts().size(); j++){
                Account account = customer.getAccounts().get(j);
                if (Objects.equals(accountNumber, account.getAccountNumber()))
                    return true;
            }
        }
            return false;
        }
    public ArrayList<Customer> getCustomer(String customerId) {
        return customers;
    }
}
