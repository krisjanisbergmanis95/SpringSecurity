package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.repos.ICustomerRepo;
import com.example.demo.repos.IProductRepo;
import com.example.demo.services.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	
	@Autowired
	ICustomerRepo custRepo;
	
	@Autowired
	IProductRepo prodRepo;
	
	@Override
	public boolean register(String name, int age) {//name - as a username and unique
		if(custRepo.existsByNameAndAge(name, age))
		{
			return false;
		}
		
		custRepo.save(new Customer(name, age));
		return true;
		
	}

	@Override
	public ArrayList<Product> getAllPurchasedProductsByCustId(int id) throws Exception{
		
		if(id>0)
		{
			if(custRepo.existsById(id))
			{
				Customer cust = custRepo.findById(id).get();
				
				ArrayList<Product> purchasedProduct = prodRepo.findByCustomer(cust);
				
				return purchasedProduct;
				
				
			}
		}
		throw new Exception("There is no customer with specific id in the System");

	}

	@Override
	public boolean buyProducts(Collection<Product> purchasedProducts, int id) throws Exception {
		if(id>0)
		{
			if(custRepo.existsById(id))
			{
				Customer cust = custRepo.findById(id).get();
				for(Product p:purchasedProducts )
				{
					Product prod = prodRepo.findByTitleAndPrice(p.getTitle(), p.getPrice());
					prod.setCustomer(cust);
					prodRepo.save(prod);
					
				}
			
			}
			return true;
		}
		throw new Exception("There is no customer with specific id in the System");

	}

	@Override
	public Customer selectOneCustomerById(int id) throws Exception {
		
		if(id>0)
		{
			if(custRepo.existsById(id))
			{
				return custRepo.findById(id).get();
			}
		}
		throw new Exception("There is no customer with specific id in the System");
	}

	@Override
	public ArrayList<Customer> selectAllCustomers() {
		// TODO Auto-generated method stub
		return (ArrayList<Customer>) custRepo.findAll();
	}

}
