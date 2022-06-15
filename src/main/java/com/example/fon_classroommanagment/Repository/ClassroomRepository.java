package com.example.fon_classroommanagment.Repository;

import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.Classroom.ClassroomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom,Long>, PagingAndSortingRepository<Classroom,Long>{

@Query(value = "select  c from Classroom  c where  c.number_of_seats BETWEEN :min_capacity AND :max_capacity  and c.aircondition=:airconditionParam and c.projector=:projectorParam")
List<Classroom> filter(@Param("min_capacity") int min_capacity, @Param("max_capacity") int max_capacity,@Param("airconditionParam") boolean airconditionParam,@Param("projectorParam") boolean projectorParam);

    @Query(value = "select  c from Classroom  c where  c.number_of_seats BETWEEN :min_capacity AND :max_capacity and  c.type.id=:type")
    List<Classroom> filterWithType(@Param("min_capacity") int min_capacity, @Param("max_capacity") int max_capacity, @Param("type") Long type);

    @Query(value = "select  c from Classroom  c where  c.number_of_seats BETWEEN :min_capacity AND :max_capacity and  c.type.id=:type order by c.number_of_seats")
    List<Classroom> filterAll(@Param("min_capacity") int min_capacity, @Param("max_capacity") int max_capacity,@Param("type") Long type);

    @Query(value = "select  c from  Classroom  c where c.name like :name   ")
    Page<Classroom> findByNameContaining(@Param("name") String name,Pageable pageable);

    @Query(value = "select c from ClassroomType c")
    List<ClassroomType> getAllClassroomTypes();
}
