package com.example.mapping.onetomany.unidirectional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post-unidirectional")
public class PostUniController {

	private PostUniDirectionalRepository repository;
	private PostCommentUniDirectionalRepository uniDirectionalRepo;

	public PostUniController(PostUniDirectionalRepository repository,
			PostCommentUniDirectionalRepository uniDirectionalRepo) {
		this.repository = repository;
		this.uniDirectionalRepo = uniDirectionalRepo;
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> get(@PathVariable Long id) {
		PostUniDirectional postUniDirectional = this.repository.findById(id).get();
		this.repository.save(postUniDirectional);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping
	@Transactional
	public void save() {
		PostUniDirectional postUniDirectional = new PostUniDirectional();
		postUniDirectional.setTitle("Post Unidirectional 1");
		List<PostCommentUnidirectional> commnentList = new ArrayList<>();
		PostCommentUnidirectional postCommentUnidirectional1 = new PostCommentUnidirectional();
		postCommentUnidirectional1.setReview("Post Comment Uni 1");
		commnentList.add(postCommentUnidirectional1);
		PostCommentUnidirectional postCommentUnidirectional2 = new PostCommentUnidirectional();
		postCommentUnidirectional2.setReview("Post Comment Uni 2");
		commnentList.add(postCommentUnidirectional2);
		postUniDirectional.setComments(commnentList);
		this.repository.save(postUniDirectional);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		PostUniDirectional post = this.repository.findById(id).get();
		post.getComments().remove(0);
	}

}