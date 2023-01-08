package com.telkomsel.kezbek.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telkomsel.kezbek.auth.model.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail,Integer> {

    Optional<UserDetail> findByUsername(String name);

	boolean existsByUsername(String name);

}
