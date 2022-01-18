package com.remotemode.nternship3inventoryconsumer.repository;

import com.remotemode.nternship3inventoryconsumer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
