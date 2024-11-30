package com.example.capstone1.repository;

import com.example.capstone1.domain.Postimg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostimgRepository extends JpaRepository<Postimg, Long>{
}
