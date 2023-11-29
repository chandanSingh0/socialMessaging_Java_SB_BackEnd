package com.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Integer> {
	//if we going to find the reels with the name of the user then we write here -->> findByUserFirstName
	public List<Reels> findByUserId(Integer userId);
	
}
