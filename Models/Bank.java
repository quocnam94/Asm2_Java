package com.NamTQ.Models;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Bank{
    private  String id;
    private  ArrayList<Customer> customers;
    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
        this.customers = new ArrayList<Customer>();
    }
    public String getId() {
        return id;
    }
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    public void addCustomer(Customer newCustomer){
        customers.add(newCustomer);
    }
    public void addAccount(String customerId, Account newAccount){
        for(int i =0; i<customers.size(); i++){
            if(Objects.equals(customerId,customers.get(i).getCustomerID())){
                customers.get(i).addAccount(newAccount);
            }
        }
    }
    public boolean isCustomerExisted(String customerId){
        for (int i =0; i<customers.size(); i++){
            if(Objects.equals(customerId,customers.get(i).getCustomerID())){
                return true;
            }
        }
        return false;
    }
}
