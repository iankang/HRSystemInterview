package com.kangethe.hrsystem.repositories;


import com.kangethe.hrsystem.entities.ERole;
import com.kangethe.hrsystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
