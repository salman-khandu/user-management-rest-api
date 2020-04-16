package com.example.mapping.onetoone.bidirectional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapping.onetomany.bidirectional.PostBiDirectionalRepository;
import com.example.mapping.onetomany.unidirectional.PostCommentUniDirectionalRepository;
import com.example.mapping.onetomany.unidirectional.PostUniDirectionalRepository;
import com.example.mapping.onetoone.mapsid.Address;
import com.example.mapping.onetoone.mapsid.AddressRepository;
import com.example.mapping.onetoone.mapsid.Library;
import com.example.mapping.onetoone.mapsid.LibraryRepository;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

	private PostRepository postRepository;

	@Autowired
	public PostController(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> get(@PathVariable Long id) {
		Post post = this.postRepository.findById(id).get();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}

@Component
class CommandLineAppStartupRunner implements CommandLineRunner {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostUniDirectionalRepository postUniDirectionalRepository;

	@Autowired
	private LibraryRepository libraryRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PostBiDirectionalRepository postBiDirectionalRepository;
	@Autowired
	private PostCommentUniDirectionalRepository postCommentUniDirectionalRepository;

	@Override
	public void run(String... args) throws Exception {
		Post post = new Post();
		post.setTitle("post 2");
		PostDetails details = new PostDetails();
		details.setCreatedBy("user 2");
		post.setPostDetails(details);
		details.setPost(post);
		this.postRepository.save(post);

		// One To One with MapsId

		Library library = new Library();
		library.setName("Lib Test 1");
		Address address = new Address();
		address.setStreet("street 1");
		address.setZipCode("3900001");
		address.setLibrary(library);
		library.setAddress(address);

		this.libraryRepository.save(library);
		// this.addressRepository.save(address);

	}
}