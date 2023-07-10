package com.sisinfo.Repository;

import com.sisinfo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.email = :email")
    boolean existsByEmail(@Param("email") String email);

    boolean existsByTelephone(long telephone);

    boolean existsByNameOrEmail(String name, String email);

    Employee findByEmail(String email);

    Employee findByTelephone(long telephone);
}
