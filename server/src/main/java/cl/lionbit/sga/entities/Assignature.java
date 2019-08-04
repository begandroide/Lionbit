package cl.lionbit.sga.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Assignature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;
	private String name;
	private String sigla;
	private Integer creditos_usm;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "assignature")
	private Set<AssignatureAndSemester> courses;

	public Assignature() {
	}
	

	public Assignature(Long id, String name, String sigla, Integer creditos_usm) {
		this.Id = id;
        this.name = name;
        this.sigla = sigla;
		this.creditos_usm = creditos_usm;
	}

	@Override
	public String toString() {
		return "Assignature[id=" + Id + ", name=" + name + "]";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.Id = id;
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
	 * @param num_paralelos the number of paralelos of assignature
	 */
	public void setCreditos_usm(Integer creditos_usm) {
		this.creditos_usm = creditos_usm;
	}

	/**
	 * @return the number of paralelos of assignature
	 */
	public Integer getCreditos_usm() {
		return creditos_usm;
	}
	

}
