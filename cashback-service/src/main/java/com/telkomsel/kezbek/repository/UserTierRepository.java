package com.telkomsel.kezbek.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telkomsel.kezbek.model.UserTier;

public interface UserTierRepository extends JpaRepository<UserTier, String> {
	
	Optional<UserTier> findByEmailAndPartnerId(String email, String partnerId);

}
