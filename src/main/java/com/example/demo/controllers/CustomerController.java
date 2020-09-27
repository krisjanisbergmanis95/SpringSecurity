package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.services.ICustomerService;
import com.example.demo.services.IProductService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	ICustomerService custService;
	
	@Autowired
	IProductService prodService;
	
	
	@GetMapping("/showMyProducts/{id}") //url address->localhost:8080/customer/showMyProducts/1
	public String getShowMyProductsByCustId(@PathVariable (name="id") int id, Model model)
	{
		System.out.println(id);
		try {
			model.addAttribute("innerObjectProd", custService.getAllPurchasedProductsByCustId(id));
			model.addAttribute("innerObjectCustName", custService.selectOneCustomerById(id).getName());
			return "show-all-customer-products-page";//show-all-customer-products-page.html
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "error";
		}
		
	}
	
	//showAllCustomers
	@GetMapping("/showAllCustomers")
	public String getShowAllCustomers(Model model)
	{
		model.addAttribute("innerObject", custService.selectAllCustomers());
		
		return "show-all-customers-page";//show-all-customers-page.html
	}
	
	
	//insertNewCustomer or register
		@GetMapping("/register")// url address->localhost:8080/customer/register
		public String getInsertOneCustomer(Customer customer)//it's empty product for data input
		{
			return "insert-one-customer-page";//insert-one-customer-page.html
		}
		
		@PostMapping("/register")//it will call when submit button will be pressed
		public String postInsertOneCustomer(@Valid Customer customer, BindingResult result)
		{
			System.out.println(customer);
			
			if(result.hasErrors())
			{
				return "insert-one-customer-page";
			}
					
			//prodService.insertNewProduct(product.getTitle(), product.getPrice());
			custService.register(customer.getName(), customer.getAge());
			return "redirect:/customer/showAllCustomers";
			
		}
	
	//buyProducts
	@GetMapping("/buy/{id}")
	public String getBuyByCustId(@PathVariable (name="id") int id, Model model, Customer customer)	
	{
		try {
			model.addAttribute("innerObjectCustName", custService.selectOneCustomerById(id).getName());
			model.addAttribute("allCustomerProducts", prodService.selectAllProducts());
			
			return "customer-buy";//customer-buy.html
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	
	}
	//lcoalhost:8080/customer/buy/2
	
	@PostMapping("/buy/{id}")
	public String postBuyByCustId(@PathVariable (name="id") int id, Customer customer)
	{
		for(Product prod : customer.getAllCustomerProducts())
			System.out.println(prod.getTitle() + " " + prod.getPrice());
		
		try {
			custService.buyProducts(customer.getAllCustomerProducts(), id);
			System.out.println("!!!");
			return "redirect:/customer/showMyProducts/"+id;
			//localhost:8080/customer/showMyProducts/2
		} catch (Exception e) {
			System.out.println(e);
			return "error";
		}
		
	}
	
	
	
}
