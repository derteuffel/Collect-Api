package com.derteuffel.repository;

import com.derteuffel.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by derteuffel on 20/10/2018.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);

}
