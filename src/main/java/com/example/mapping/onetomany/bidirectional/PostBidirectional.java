package com.example.mapping.onetomany.bidirectional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "PostBidirectional")
@Table(name = "post_bidirectional")
public class PostBidirectional {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	private String title;


    @OneToMany(
        mappedBy = "postBidirectional",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )

	private List<PostCommentBidirectonal> comments = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<PostCommentBidirectonal> getComments() {
		return comments;
	}

	public void setComments(List<PostCommentBidirectonal> comments) {
		this.comments = comments;
	}

}
