package com.github.fsamin.models

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import java.util.List

@Document(indexName = "user", type = "user")
public class User implements UserDetails {

    @Id
    Long id;
    String email;
    String password;

    /* Spring Security fields*/
    @Field(type = FieldType.Nested)
    List<Role> authorities = new ArrayList<Role>();

    Boolean accountNonExpired = true;
    Boolean accountNonLocked = true;
    Boolean credentialsNonExpired = true;
    Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String toString() {
        return id + ";" + email;
    }
}

public class Role implements GrantedAuthority {

    String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}

