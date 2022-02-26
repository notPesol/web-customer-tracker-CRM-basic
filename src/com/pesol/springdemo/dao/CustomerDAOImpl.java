package com.pesol.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pesol.springdemo.entity.Customer;
import com.pesol.springdemo.util.SortUtil;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query // org.hibernate.query.Query; // and sort by firstName
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer c ORDER BY c.firstName", Customer.class);

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer newCustomer) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save the customer ... or update existing customer
		currentSession.saveOrUpdate(newCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// retrieve/read from database using primary key (id)
		Customer customer = currentSession.get(Customer.class, theId);

		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete customer by primary key (id) from database
		Query theQuery = 
				currentSession.createQuery("DELETE FROM Customer c WHERE c.id = :customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomer(String theSearchName) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		// create the query
		Query<Customer> theQuery;
		
		// only search if theSearchName not null or not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			theQuery = 
					currentSession.createQuery("from Customer "
							+ "WHERE lower(firstName) LIKE :theName OR lower(lastName) LIKE :theName "
							+ "ORDER BY firstName, lastName",
					Customer.class);
			theQuery.setParameter("theName", '%' + theSearchName + '%');
		} else {
			// get all customers if theSearch is empty
			theQuery = 
					currentSession.createQuery("from Customer", Customer.class);
		}
		
		// execute query and get the result
		List<Customer> customers = theQuery.getResultList();
		
		// return the result
		return customers;
	}

	@Override
	public List<Customer> getCustomers(int theSort) {
		
		// get hibernate current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// determine sort field
		String sortBy = null;
		switch (theSort) {
		case SortUtil.BY_LASTNAME:
			sortBy = "lastName";
			break;
		case SortUtil.BY_EMAIL:
			sortBy = "email";
			break;

		default:
			sortBy = "firstName";
			break;
		}
		
		// create query
		String queryString = "FROM Customer ORDER BY " + sortBy;
		Query<Customer> theQuery = 
				currentSession.createQuery(queryString, Customer.class);
		
		// execute query and get result
		List<Customer> customers = theQuery.getResultList();
		
		// return the result
		return customers;
	}

}
