package com.example.mapping.onetomany.bidirectional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/post-bidirectional")
public class PostBidirectionalController {

	private PostBiDirectionalRepository repository;
	private PostBiDirectionalRepository biDirectionalRepo;

	@Autowired
	public PostBidirectionalController(PostBiDirectionalRepository repository,
			PostBiDirectionalRepository biDirectionalRepo) {
		this.repository = repository;
		this.biDirectionalRepo = biDirectionalRepo;
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> get(@PathVariable Long id) {
		System.out.println("Before get parent " + new Date());
		PostBidirectional postBidirectional = this.repository.findById(id).get();
		System.out.println("After get parent " + new Date());
		postBidirectional.getComments().size();
		System.out.println("After get child " + new Date());
		this.repository.save(postBidirectional);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Transactional
	@PostMapping
	public void save() {
		PostBidirectional postBidirectional = new PostBidirectional();
		postBidirectional.setTitle("Post bidirectional 1");
		List<PostCommentBidirectonal> commnentBiDiList = new ArrayList<>();
		PostCommentBidirectonal postCommentbidirectional1 = new PostCommentBidirectonal();
		postCommentbidirectional1.setReview("Post Comment Bidirectoinal 1");
		postCommentbidirectional1.setPostBidirectional(postBidirectional);
		commnentBiDiList.add(postCommentbidirectional1);

		PostCommentBidirectonal postCommentbidirectional2 = new PostCommentBidirectonal();
		postCommentbidirectional2.setReview("Post Comment Bidirectoinal 2");
		commnentBiDiList.add(postCommentbidirectional2);
		postCommentbidirectional2.setPostBidirectional(postBidirectional);

		postBidirectional.setComments(commnentBiDiList);
		this.biDirectionalRepo.save(postBidirectional);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		PostBidirectional post = this.biDirectionalRepo.findById(id).get();
		post.getComments().remove(0);
	}

}