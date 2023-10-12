package edu.cs321.group5.moolah.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.cs321.group5.moolah.model.UserData;

public interface UserRepository extends MongoRepository<UserData, String> {
	
	@Query("{name:'?0'}")
	UserData findItemByName(String name);
	
	@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
	List<UserData> findAll(String category);
	
	public long count();

}