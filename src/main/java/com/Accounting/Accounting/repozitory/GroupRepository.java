package com.Accounting.Accounting.repozitory;

import com.Accounting.Accounting.models.Grouper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Grouper, Long> {
}
