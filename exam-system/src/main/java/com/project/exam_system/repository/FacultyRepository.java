package com.project.exam_system.repository;

import com.project.exam_system.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {

    @Query("select f from Faculty f where f.username = :username")
    Faculty findExistByUsername(String username);

    Faculty findByUsername(String username);
}
