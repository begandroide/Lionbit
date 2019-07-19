package cl.lionbit.sga.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Assignature {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String sigla;
    private Integer num_paralelos;
    private Long num_students;
    

	public Assignature() {
	}
	

	public Assignature(Long id, String name, String sigla, Integer num_paralelos, Long num_students) {
		this.id = id;
        this.name = name;
        this.sigla = sigla;
        this.num_paralelos = num_paralelos;
        this.num_students = num_students;
	}

	@Override
	public String toString() {
		return "Assignature[id=" + id + ", name=" + name + "]";
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
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}
	
	/**
	 * @param sigla the sigla of assignature
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @param num_students the number of students of assignature
	 */
	public void setNum_students(Long num_students) {
		this.num_students = num_students;
	}

	/**
	 * @return the number of students
	 */
	public Long getNum_students() {
		return num_students;
	}

	/**
	 * @param num_paralelos the number of paralelos of assignature
	 */
	public void setNum_paralelos(Integer num_paralelos) {
		this.num_paralelos = num_paralelos;
	}

	/**
	 * @return the number of paralelos of assignature
	 */
	public Integer getNum_paralelos() {
		return num_paralelos;
	}


}
