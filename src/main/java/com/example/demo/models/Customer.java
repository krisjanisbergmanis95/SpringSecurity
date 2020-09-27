package com.example.demo.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Table(name="CustomerTable")
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Cid")
	private int cId;
	
	@Size(min = 3, max = 15)
	@Pattern(regexp="[a-zA-Z\\s]+$", message = "Only letters and spaces allowed")
	@Column(name="Name")
	private String name;
	
	
	@Min(5)
	@Max(100)
	@Column(name="Age")
	private int age;

	
	@OneToMany(mappedBy="customer")
	private Collection<Product> allCustomerProducts;
	
	
	public Customer()
	{
		
	}
	
	
	public Customer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}


	public Collection<Product> getAllCustomerProducts() {
		return allCustomerProducts;
	}


	public void setAllCustomerProducts(Collection<Product> allCustomerProducts) {
		this.allCustomerProducts = allCustomerProducts;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public int getcId() {
		return cId;
	}


	@Override
	public String toString() {
		return "Customer [cId=" + cId + ", name=" + name + ", age=" + age + "]";
	}


	
	
	
	

}
