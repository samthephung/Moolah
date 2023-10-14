package edu.cs321.group5.moolah;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import edu.cs321.group5.moolah.model.UserItem;
import edu.cs321.group5.moolah.repository.CustomItemRepository;
import edu.cs321.group5.moolah.repository.ItemRepository;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication implements CommandLineRunner{
	
	@Autowired
	ItemRepository UserItemRepo;
	
	@Autowired
	CustomItemRepository customRepo;
	
	List<UserItem> itemList = new ArrayList<UserItem>();

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}
	
	public void run(String... args) {
		
		// Clean up any previous data
		UserItemRepo.deleteAll(); // Doesn't delete the collection
		
		System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");
		
		createUserItems();
		
		System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");
		
		showAllUserItems();
		
		System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");
		
		getUserItemByName("Whole Wheat Biscuit");
		
		System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");
		
		getItemsByCategory("millets");
		
		System.out.println("\n-----------UPDATE CATEGORY NAME OF ALL GROCERY ITEMS----------------\n");
		
		updateCategoryName("snacks");
		
		System.out.println("\n-----------UPDATE QUANTITY OF A GROCERY ITEM------------------------\n");
		
		updateItemQuantity("Bonny Cheese Crackers Plain", 10);
		
		System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");
		
		deleteUserItem("Kodo Millet");
		
		System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");
		
		findCountOfUserItems();
		
		System.out.println("\n-------------------THANK YOU---------------------------");
						
	}
	
	// CRUD operations

	//CREATE
	void createUserItems() {
		System.out.println("Data creation started...");

		UserItemRepo.save(new UserItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
		UserItemRepo.save(new UserItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
		UserItemRepo.save(new UserItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
		UserItemRepo.save(new UserItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
		UserItemRepo.save(new UserItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
		
		System.out.println("Data creation complete...");
	}
	
	// READ
	// 1. Show all the data
	 public void showAllUserItems() {
		 
		 itemList = UserItemRepo.findAll();
		 
		 itemList.forEach(item -> System.out.println(getItemDetails(item)));
	 }
	 
	 // 2. Get item by name
	 public void getUserItemByName(String name) {
		 System.out.println("Getting item by name: " + name);
		 UserItem item = UserItemRepo.findItemByName(name);
		 System.out.println(getItemDetails(item));
	 }
	 
	 // 3. Get name and items of a all items of a particular category
	 public void getItemsByCategory(String category) {
		 System.out.println("Getting items for the category " + category);
		 List<UserItem> list = UserItemRepo.findAll(category);
		 
		 list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getItemQuantity()));
	 }
	 
	 // 4. Get count of documents in the collection
	 public void findCountOfUserItems() {
		 long count = UserItemRepo.count();
		 System.out.println("Number of documents in the collection = " + count);
	 }
	 
	 // UPDATE APPROACH 1: Using MongoRepository
	 public void updateCategoryName(String category) {
		 
		 // Change to this new value
		 String newCategory = "munchies";
		 
		 // Find all the items with the category 
		 List<UserItem> list = UserItemRepo.findAll(category);
		 
		 list.forEach(item -> {
			 // Update the category in each document
			 item.setCategory(newCategory);
		 });
		 
		 // Save all the items in database
		 List<UserItem> itemsUpdated = UserItemRepo.saveAll(list);
		 
		 if(itemsUpdated != null)
			 System.out.println("Successfully updated " + itemsUpdated.size() + " items.");		 
	 }
	 
	 
	 // UPDATE APPROACH 2: Using MongoTemplate
	 public void updateItemQuantity(String name, float newQuantity) {
		 System.out.println("Updating quantity for " + name);
		 customRepo.updateItemQuantity(name, newQuantity);
	 }
	 
	 // DELETE
	 public void deleteUserItem(String id) {
		 UserItemRepo.deleteById(id);
		 System.out.println("Item with id " + id + " deleted...");
	 }
	 // Print details in readable form
	 
	 public String getItemDetails(UserItem item) {

		 System.out.println(
		 "Item Name: " + item.getName() + 
		 ", \nItem Quantity: " + item.getItemQuantity() + 
		 ", \nItem Category: " + item.getCategory()
		 );
		 
		 return "";
	 }
}
