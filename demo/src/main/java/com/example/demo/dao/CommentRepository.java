package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Patient;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	@Query("Select c from Comment c where c.patient=?1")
	List<Comment> findByPatient(@RequestParam Patient patient);

}
