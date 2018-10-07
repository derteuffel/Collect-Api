package com.derteuffel.repository;

import com.derteuffel.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by derteuffel on 06/10/2018.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
