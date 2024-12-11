package com.Accounting.Accounting.repozitory;


import com.Accounting.Accounting.models.Useres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UseresRepository extends JpaRepository<Useres, Long> {
    Optional<Useres> findByUsername(String username);
}
