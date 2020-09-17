package com.shen.redditclone.services;

import com.shen.redditclone.domain.Role;
import com.shen.redditclone.repositery.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(String name){
        return roleRepository.findByName(name);
    }
}
