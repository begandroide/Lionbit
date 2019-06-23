package cl.lionbit.sga.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ApiModel(description = "All details about the Student. ")
public class Student {

	@Id
	@GeneratedValue
	@ApiModelProperty(notes = "The database generated student ID")
	private Long id;
	private String name;
	private String rol_usm;
    

	public Student() {
	}
	

	public Student(Long id, String name, String rol) {
		this.id = id;
		this.name = name;
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
