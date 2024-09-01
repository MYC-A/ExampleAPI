package com.GreenAtomTask.ExampleAPI.repository;

import com.GreenAtomTask.ExampleAPI.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,Integer> {
    Page<File> findAllByOrderByDateAsc(Pageable pageable);
}
