package com.example.minishoppingoutlet.controller;


import com.example.minishoppingoutlet.data.dtos.response.ApiResponse;
import com.example.minishoppingoutlet.data.models.Role;
import com.example.minishoppingoutlet.exceptions.UserRoleNotFoundException;
import com.example.minishoppingoutlet.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/miniStore/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/allRoles")
    public ResponseEntity<?>getAllRoles(){
        List<Role> getAllRolesInDb = roleService.getAllRoles();
        return ResponseEntity.status(HttpStatus.OK).body(getAllRolesInDb);
    }

    @GetMapping("{roleName}")
    public ResponseEntity<?> findRoleByName(@PathVariable  String roleName){

        try{
            Role foundRole = roleService.findByName(roleName);
            return new ResponseEntity<>(foundRole, HttpStatus.OK);
        }catch(UserRoleNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "No role found by that name"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/newRole")
    public ResponseEntity<?>createANewRole(@RequestBody Role role){
        try{
            roleService.createNewRole(role);
            return new ResponseEntity<>(new ApiResponse(true, "Role Created Successfully"), HttpStatus.CREATED);
        }catch (UserRoleNotFoundException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
