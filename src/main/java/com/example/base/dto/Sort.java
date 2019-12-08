/**
 * 
 */
package com.example.base.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Salman.Khandu
 *
 */
public class Sort {

	private List<SortAttribute> attributes = new ArrayList<>();

	public List<SortAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SortAttribute> attributes) {
		this.attributes = attributes;
	}

	@JsonIgnore
	public org.springframework.data.domain.Sort buildSpringSortObject() {
		List<Order> orders = new ArrayList<>();
		this.attributes.forEach(attribute -> {
			Order order = new Order(Direction.fromString(attribute.getDirection().toString()), attribute.getProperty());
			orders.add(order);
		});

		return org.springframework.data.domain.Sort.by(orders);

	}

}
