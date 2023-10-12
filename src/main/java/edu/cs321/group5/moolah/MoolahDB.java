package edu.cs321.group5.moolah;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import edu.cs321.group5.moolah.model.UserData;
import edu.cs321.group5.moolah.repository.CustomUserRepository;
import edu.cs321.group5.moolah.repository.UserRepository;

@SpringBootApplication
@EnableMongoRepositories
public class MoolahDB implements CommandLineRunner{
	
	@Autowired
	UserRepository userDataRepo;
	
	@Autowired
	CustomUserRepository customRepo;
	
	List<UserData> itemList = new ArrayList<UserData>();

	public static void main(String[] args) {
		SpringApplication.run(MoolahDB.class, args);
	}
	
	public void run(String... args) {
		
		// Clean up any previous data
		userDataRepo.deleteAll(); // Doesn't delete the collection
		
		System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");
		
		createUserDatas();
		
		System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");
		
		showAllUserDatas();
		
		System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");
		
		getUserDataByName("Whole Wheat Biscuit");
		
		System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");
		
		getItemsByCategory("millets");
		
		System.out.println("\n-----------UPDATE CATEGORY NAME OF ALL GROCERY ITEMS----------------\n");
		
		updateCategoryName("snacks");
		
		System.out.println("\n-----------UPDATE QUANTITY OF A GROCERY ITEM------------------------\n");
		
		updateItemQuantity("Bonny Cheese Crackers Plain", 10);
		
		System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");
		
		deleteUserData("Kodo Millet");
		
		System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");
		
		findCountOfUserDatas();
		
		System.out.println("\n-------------------THANK YOU---------------------------");
						
	}
	
	// CRUD operations

	//CREATE
	void createUserDatas() {
		System.out.println("Data creation started...");

		userDataRepo.save(new UserData("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
		userDataRepo.save(new UserData("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
		userDataRepo.save(new UserData("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
		userDataRepo.save(new UserData("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
		userDataRepo.save(new UserData("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
		
		System.out.println("Data creation complete...");
	}
	
	// READ
	// 1. Show all the data
	 public void showAllUserDatas() {
		 
		 itemList = userDataRepo.findAll();
		 
		 itemList.forEach(item -> System.out.println(getItemDetails(item)));
	 }
	 
	 // 2. Get item by name
	 public void getUserDataByName(String name) {
		 System.out.println("Getting item by name: " + name);
		 UserData item = userDataRepo.findItemByName(name);
		 System.out.println(getItemDetails(item));
	 }
	 
	 // 3. Get name and items of a all items of a particular category
	 public void getItemsByCategory(String category) {
		 System.out.println("Getting items for the category " + category);
		 List<UserData> list = userDataRepo.findAll(category);
		 
		 list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getItemQuantity()));
	 }
	 
	 // 4. Get count of documents in the collection
	 public void findCountOfUserDatas() {
		 long count = userDataRepo.count();
		 System.out.println("Number of documents in the collection = " + count);
	 }
	 
	 // UPDATE APPROACH 1: Using MongoRepository
	 public void updateCategoryName(String category) {
		 
		 // Change to this new value
		 String newCategory = "munchies";
		 
		 // Find all the items with the category 
		 List<UserData> list = userDataRepo.findAll(category);
		 
		 list.forEach(item -> {
			 // Update the category in each document
			 item.setCategory(newCategory);
		 });
		 
		 // Save all the items in database
		 List<UserData> itemsUpdated = userDataRepo.saveAll(list);
		 
		 if(itemsUpdated != null)
			 System.out.println("Successfully updated " + itemsUpdated.size() + " items.");		 
	 }
	 
	 
	 // UPDATE APPROACH 2: Using MongoTemplate
	 public void updateItemQuantity(String name, float newQuantity) {
		 System.out.println("Updating quantity for " + name);
		 customRepo.updateItemQuantity(name, newQuantity);
	 }
	 
	 // DELETE
	 public void deleteUserData(String id) {
		 userDataRepo.deleteById(id);
		 System.out.println("Item with id " + id + " deleted...");
	 }
	 // Print details in readable form
	 
	 public String getItemDetails(UserData item) {

		 System.out.println(
		 "Item Name: " + item.getName() + 
		 ", \nItem Quantity: " + item.getItemQuantity() + 
		 ", \nItem Category: " + item.getCategory()
		 );
		 
		 return "";
	 }
}