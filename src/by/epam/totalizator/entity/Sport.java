package by.epam.totalizator.entity;

import java.io.Serializable;

public class Sport implements Serializable {
	
	private static final long serialVersionUID =  1L;
	
	private int id;
	private String name;
	
	public Sport() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Sport other = (Sport) obj;
		
		if (name != null ? !name.equals(other.name) : other.name != null) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + "id : " + id + ", name : " + name;
	}

}
