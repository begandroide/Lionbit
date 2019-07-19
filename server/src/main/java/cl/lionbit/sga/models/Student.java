package cl.lionbit.sga.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String last_name;
	private String rol_usm;
    

	public Student() {
	}
	

	public Student(Long id, String name, String last_name, String rol) {
		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.rol_usm = rol;
	}

	@Override
	public String toString() {
		return "Student[id=" + id + ", name=" + name + "]";
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
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the rol_usm
	 */
	public String getRol_usm() {
		return rol_usm;
	}

	/**
	 * @param rol_usm the rol_usm to set
	 */
	public void setRol_usm(String rol_usm) {
		this.rol_usm = rol_usm;
	}

}
