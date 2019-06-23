package cl.lionbit.sga.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name="students")
@ApiModel(description = "All details about the Student. ")
public class Student implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated student ID")
	private Long id;

	@NotEmpty
	@Column(name="name_student", length = 40)
	private String name;

	@NotEmpty
	@Column(name="lastname_student", length = 40)
	private String lastname;

	@NotEmpty
	@Column(name="rol_usm_student", length = 20, unique = true, updatable = false)
	private String rol_usm;
    

	public Student() {
	}
	

	public Student(Long id, String name, String lastname, String rol) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.rol_usm = rol;
	}

	@Override
	public String toString() {
		return "Student[id=" + id + ", name=" + name + lastname + "]";
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
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the name to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	private static final long serialVersionUID = 1L;
}
