package com.shopit.common.entity;

import java.util.Objects;

import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;

	@Column(length = 40, nullable = false, unique = true)
	private String name;

	@Column(length = 150, nullable = false)
	private String description;

	

	public Role(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Role() {
		super();
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Role(Integer iD) {
		super();
		ID = iD;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(ID, other.ID);
	}
	
	

}
