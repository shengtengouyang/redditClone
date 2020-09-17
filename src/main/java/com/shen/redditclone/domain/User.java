package com.shen.redditclone.domain;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.print.attribute.HashAttributeSet;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    @Email @Column(unique = true)
    private String email;

    @NonNull
    @Column(length = 100)
    private String password;

    @NonNull
    @Column(nullable = false)
    private boolean enabled;

    @NonNull
    @NotEmpty(message = "You must enter First Name.")
    private String firstName;

    @NonNull
    @NotEmpty(message = "You must enter Last Name.")
    private String lastName;

    @Transient
    @Setter(AccessLevel.NONE)
    private String fullName;

    @NonNull
    @NotEmpty(message = "Please enter alias.")
    @Column(nullable = false, unique = true)
    private String alias;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> roles=new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public void addRoles(Set<Role> roles){
        roles.forEach(this::addRole);
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFullName(){
        return firstName+" "+lastName;
    }
}
