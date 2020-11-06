package com.p990r.springexample.model;

import com.p990r.springexample.api.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
