package com.example.mapping.onetomany.bidirectional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "PostCommentBidirectonal")
@Table(name = "post_comment_bidirectional")
public class PostCommentBidirectonal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	private String review;

	@ManyToOne(fetch = FetchType.LAZY)
	private PostBidirectional postBidirectional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public PostBidirectional getPostBidirectional() {
		return postBidirectional;
	}

	public void setPostBidirectional(PostBidirectional postBidirectional) {
		this.postBidirectional = postBidirectional;
	}

}
