package com.pesol.springdemo.service;

import java.util.List;

import com.pesol.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer newCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomer(String theSearchName);

	public List<Customer> getCustomers(int theSort);
}
