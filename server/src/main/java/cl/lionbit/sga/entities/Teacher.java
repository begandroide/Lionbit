package cl.lionbit.sga.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;
@Entity
@Table(name="teachers")
@ApiModel(description = "All details about the Teacher. ")
public class Teacher implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated teacher ID")
	private Long id;

	@NotEmpty
	@Column(name="name", length = 40)
	private String name;

	@NotEmpty
	@Column(name="lastname", length = 40)
	private String lastname;

	@NotEmpty
	@Column(name="rut", length = 20, unique = true, updatable = false)
	private String rut;
	
	@OneToMany
	@JoinColumn(name="teacher_id")
	private Set<TeacherHasACourse> courses;

	public Teacher() {
	}
	

	public Teacher(Long id, String name, String rut) {
		this.id = id;
		this.name = name;
		this.rut = rut;
	}

	@Override
	public String toString() {
		return "Teacher[id=" + id + ", name=" + name + lastname+ "]";
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

	private static final long serialVersionUID = 1L;

}
