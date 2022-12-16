package com.codegym.casemodule4be.service;


import com.codegym.casemodule4be.model.Role;

public interface RoleService {
    Iterable<Role> findAll();

    void save(Role role);

    Role findByName(String name);
}
