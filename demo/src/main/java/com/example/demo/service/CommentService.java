package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CommentRepository;
import com.example.demo.domain.Comment;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public Comment create(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public Comment update(Integer id) {
		Comment comment = new Comment();
		Comment commentFromDB = commentRepository.getOne(id);
		comment.setDate(commentFromDB.getDate());
		comment.setText(commentFromDB.getText());
		comment.setPatient(commentFromDB.getPatient());
		comment.setId(commentFromDB.getId());
		return commentRepository.save(comment);
	}

}
