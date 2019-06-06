package cl.lionbit.sga.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Teacher {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String rut;
    

	public Teacher() {
	}
	

	public Teacher(Long id, String name, String rut) {
		this.id = id;
		this.name = name;
		this.rut = rut;
	}

	@Override
	public String toString() {
		return "Teacher[id=" + id + ", name=" + name + "]";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

}