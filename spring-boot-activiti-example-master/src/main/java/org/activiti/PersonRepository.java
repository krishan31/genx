package org.activiti;

import org.activiti.domain.Ldap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Ldap, Long> {

    Ldap findByUsername(String username);

}