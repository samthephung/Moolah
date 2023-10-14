package edu.cs321.group5.moolah.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.cs321.group5.moolah.model.UserItem;

public interface ItemRepository extends MongoRepository<UserItem, String> {
	
	@Query("{name:'?0'}")
	UserItem findItemByName(String name);
	
	@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
	List<UserItem> findAll(String category);
	
	public long count();

}