package com.example.Authentication.Controller;

import com.example.Authentication.Models.Role;
import com.example.Authentication.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createRole")
public class RoleController {

    private RoleService roleService;
    @Autowired
    RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody String name){
        return new ResponseEntity<>(roleService.createRole(name), HttpStatus.OK);
    }
}
