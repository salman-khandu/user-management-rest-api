package com.example.base.dto;

/**
 * @author Salman.Khandu
 *
 */
public class SortAttribute {

	private String property;
	private Direction direction;

	public enum Direction {
		ASC, DESC;
	}

	public SortAttribute() {
	}

	public SortAttribute(String property, Direction direction) {
		this.property = property;
		this.direction = direction;

	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
