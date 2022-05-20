package com.example.fon_classroommanagment.Repository;

import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom,Long> {

@Query(value = "select  c from Classroom  c where  c.number_of_seats BETWEEN :min_capacity AND :max_capacity ")
List<Classroom> filter(@Param("min_capacity") int min_capacity, @Param("max_capacity") int max_capacity);

    @Query(value = "select  c from Classroom  c where  c.number_of_seats BETWEEN :min_capacity AND :max_capacity and  c.type.id=:type")
    List<Classroom> filterWithType(@Param("min_capacity") int min_capacity, @Param("max_capacity") int max_capacity, @Param("type") Long type);

    @Query(value = "select  c from Classroom  c where  c.number_of_seats BETWEEN :min_capacity AND :max_capacity and  c.type.id=:type order by c.number_of_seats")
    List<Classroom> filterAll(@Param("min_capacity") int min_capacity, @Param("max_capacity") int max_capacity,@Param("type") Long type);

    List<Classroom> findByNameContaining(String name);
}
