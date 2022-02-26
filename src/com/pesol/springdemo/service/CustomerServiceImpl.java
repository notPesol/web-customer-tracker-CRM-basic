package com.pesol.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pesol.springdemo.dao.CustomerDAO;
import com.pesol.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// new to inject customer DAO
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		// get customers from customer DAO and return result
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer newCustomer) {
		
		customerDAO.saveCustomer(newCustomer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		// get the customer by customer id
		return customerDAO.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		// delete the customer by customer id
		customerDAO.deleteCustomer(theId);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomer(String theSearchName) {
		// search customer from customer DAO
		return customerDAO.searchCustomer(theSearchName);
	}

	@Override
	@Transactional
	public List<Customer> getCustomers(int theSort) {
		return customerDAO.getCustomers(theSort);
	}

}
