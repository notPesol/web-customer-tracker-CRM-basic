package com.pesol.springdemo.dao;

import java.util.List;

import com.pesol.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer newCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomer(String theSearchName);

	public List<Customer> getCustomers(int theSort);

}
