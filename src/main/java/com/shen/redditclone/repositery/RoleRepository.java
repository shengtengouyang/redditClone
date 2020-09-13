package com.shen.redditclone.repositery;

import com.shen.redditclone.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
