package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;

public interface ICustomerService {
	/*register, 
	getAllPurchasedProducts, 
	buyProducts, getOneCustomer, 
	 */
	
	boolean register(String name, int age);
	
	ArrayList<Product> getAllPurchasedProductsByCustId(int id) throws Exception;
	
	boolean buyProducts(Collection<Product> purchasedProducts, int id) throws Exception;
	
	Customer selectOneCustomerById(int id) throws Exception;
	
	ArrayList<Customer> selectAllCustomers();
	
}
