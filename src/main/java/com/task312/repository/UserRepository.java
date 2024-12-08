package com.task312.repository;




import com.task312.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   @Query("Select u from User u left join fetch u.roles where u.username=:username")
   Optional <User> findByUsername(String username);
}
