package com.example.authorizationmicroservice.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    public final static String USER_ROLE = "USER_ROLE";
    public final static String ADMIN_ROLE = "ADMIN_ROLE";
    public final static String MANAGER_ROLE = "MANAGER_ROLE";

    @Id
    String name;

    @ManyToMany(mappedBy = "roles")
    List<User> users;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
