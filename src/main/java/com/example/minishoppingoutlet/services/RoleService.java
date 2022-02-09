package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {

    Role findByName(String roleName);

    List<Role> getAllRoles();

    void createNewRole(Role role);
}
