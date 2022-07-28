package com.kangethe.hrsystem.services;

import com.kangethe.hrsystem.entities.Role;
import com.kangethe.hrsystem.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServices {

    private RoleRepository roleRepository;

    public RolesServices(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

        public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public List<Role> addRoles(List<Role> role){
        return roleRepository.saveAll(role);
    }

    public Role addRole( Role role){
        return roleRepository.save(role);
    }

    public void removeRole( Long roleId){
        Role role = roleRepository.findById(roleId).get();
        roleRepository.deleteById(roleId);
    }

    public Long getRoleCount() {
        return roleRepository.count();
    }

}
