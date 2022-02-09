package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.models.Role;
import com.example.minishoppingoutlet.data.repositories.RoleRepository;
import com.example.minishoppingoutlet.exceptions.UserRoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {

        return findByRoleName(name);
    }

    private Role findByRoleName(String roleName) {
        return roleRepository.findRoleByName(roleName).orElseThrow(()->
                new UserRoleNotFoundException("user with role " + roleName + "does not exist"));
    }

    @Override
    public List<Role> getAllRoles() {
        return getAllRolesInDatabase();
    }

    private List<Role> getAllRolesInDatabase() {
        return roleRepository.findAll();
    }

    @Override
    public void createNewRole(Role role) {
        createANewRole(role);
    }

    private void createANewRole(Role role) {
         roleRepository.save(role);
    }
}
