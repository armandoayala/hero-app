package com.arm.api.hero.repository;

import com.arm.api.hero.model.bo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

}
