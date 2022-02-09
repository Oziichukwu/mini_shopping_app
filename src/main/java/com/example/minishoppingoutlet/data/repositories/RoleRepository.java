package com.example.minishoppingoutlet.data.repositories;

import com.example.minishoppingoutlet.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findRoleByName(String roleName);
}
