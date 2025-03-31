package com.udemy.model;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 15, nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_permission",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_permission")}
    )
    private Set<Permission> permissions;

    @Column(name = "account_non_expired", nullable = false)
    private Boolean isAccountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean isAccountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean isCredentialsNonExpired;

    @Column(name = "enabled")
    private Boolean isEnabled;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Set<Permission> getAuthorities() {
        return permissions;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


    public List<String> getRoles(Set<Permission> permissions) {
        ArrayList<String> roles = new ArrayList<>();
        permissions.forEach(p -> roles.add(p.getDescription()));

        return roles;
    }


}
