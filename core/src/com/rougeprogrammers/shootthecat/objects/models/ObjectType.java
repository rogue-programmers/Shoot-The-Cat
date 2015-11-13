package com.rougeprogrammers.shootthecat.objects.models;

public class ObjectType {
	
	public Type type;
	
	public int index;

	public ObjectType(Type type, int index) {
		this.type = type;
		this.index = index;
	}
	
	public ObjectType(Type objectType) {
		this.type = objectType;
	}
	
	public enum Type {

		/** The cat. */
		CAT,
		/** The ground. */
		GROUND,
		/** The tnt. */
		TNT,
		/** The thorn. */
		THORN,
		/** The spring. */
		SPRING
		
	}
	
}
