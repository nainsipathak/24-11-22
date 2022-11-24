package com.spring.core.jdbc.main;

import java.util.List;
import java.util.Random;

import com.spring.core.jdbc.dao.CategoryDAO;
import com.spring.core.jdbc.dao.ProductDAO;
import com.spring.core.jdbc.model.Category;
import com.spring.core.jdbc.model.Product;

public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = null;
		try {
			 context =new ClassPathXmlApplicationContext("spring.xml");
			
			CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);
			
			
			Category c1 = new Category();
			
			int randm = new Random().nextInt(1000);
			
			c1.setId(randm);
			c1.setName("Car");
			c1.setDescription("Vechile");
			
			categoryDAO.save(c1);
			
			System.out.print("category is saved successfully");
			
			
			Category cus = categoryDAO.getById(randm);
			
			cus = categoryDAO.getById(randm);
		
			
			//update category by using name
			c1.setName("Car");
			categoryDAO.update(c1);
		
			
			//delete the category data by id
			List<Category> cList = categoryDAO.getAll();
			System.out.println("After delete");
			System.out.println("List of category "+cList);
			
			categoryDAO.deleteById(randm);
			
			//Display the list of category
			cList = categoryDAO.getAll();
		    System.out.println("List Of Category"+cList);
			
			//display category by using name	
			Category cat1 = categoryDAO.getByName("Laptops");
			System.out.println("Category Detail's by using name "+categoryDAO.getByName("Laptops"));

		    //display the list of category by using names
			cList = categoryDAO.getByNames("To%");
			System.out.println("List Of Categories  by using names "+cList);
		
		    System.out.println("-----------------------------");
		    System.out.println();
		    
		   
		    //PRODUCT OPERATION
			ProductDAO productDAO = context.getBean(ProductDAO.class);
			
			Product p1 = new Product();
			
			p1.setId(randm);
			p1.setName("Cars");
			p1.setPrice(500000);
			p1.setUnitInStock(100);
			p1.setDiscontinued(true);
			
			//save product	
			productDAO.save(p1);
			System.out.println("Product data  is saved successfully");
			
			//display product by using id
			Product prd = productDAO.getById(randm);
			System.out.println("Product Detail's by using id "+productDAO.getById(randm));	
				
			//update product by using price
			p1.setPrice(30000);
			productDAO.update(p1);
			
			
			//delete the product data by id
			List<Product> pList = productDAO.getAll();
			System.out.println("After delete");
			System.out.println("List of product "+pList);
			
			productDAO.deleteById(randm);
			
			//Display the list of product
			pList = productDAO.getAll();
			
			System.out.println("List of product "+pList);
			

			//display product by using name
			Product prd1 = productDAO.getByName("Nexa");
			System.out.println("Product Detail's by using name "+productDAO.getByName("Nexa"));
			
			 //display the list of product by using names
			pList = productDAO.getByNames("Car%");
			
			System.out.println("List Of Product's by using names "+pList);
			
			//display the list of product by using price
			pList = productDAO.getByBetweenPrice(30000,500000);
			
			System.out.println("List Of Product's by using price "+pList);
			 
			//display the list of product by using discontinued
			pList = productDAO.getDiscontinuedProducts();
			
			System.out.println("List Of Product's by using discontinued "+pList);

 }
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(context != null)
				context.close();
		}

	}
}