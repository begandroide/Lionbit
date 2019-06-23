package cl.lionbit.sga.entities;

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
	@ApiModelProperty(notes = "Is the primary key, the database auto generate this ID")
	private Long studentID;

	@NotEmpty
	@Column(length = 40)
	private String firstName;

	@NotEmpty
	@Column(length = 40)
	private String lastName;

	@NotEmpty
	@Column(length = 20, unique = true, updatable = false)
	private String rol_usm;
    

	public Student() {
	}

	public Student(Long id, String firstName, String lastName, String rol) {
		this.studentID = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rol_usm = rol;
	}

	@Override
	public String toString() {
		return "Student[id=" + studentID + ", firstName=" + firstName + lastName + "]";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return studentID;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.studentID = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the firstName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
