package cl.lionbit.sga.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="assignature_semester", uniqueConstraints= @UniqueConstraint(columnNames={"assignatureId", "semesterId"}))
@ApiModel(description = "This is a table resulting from a relationship many to many")
public class AssignatureAndSemester implements Serializable {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated teacher ID")
    @Column(name = "Id")
    private Long Id;

    @Column(name = "paralelos")
    private Integer numberParalelos;

    @Column(name = "numero_estudiantes_paralelo")
    private Integer numberStudentsByParalelo;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="SemesterId", referencedColumnName="Id",insertable = false, updatable = false)
    private Semester semester;

    @Column(name = "SemesterId")
    private Long semesterId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "AssignatureId",  referencedColumnName = "Id", insertable = false, updatable = false)
    private Assignature assignature;

    @Column(name = "AssignatureId")
    private Long assignatureId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignatureAndSemester")
	private Set<TeacherHasACourse> teacherHasACourses;


    public void setNumberParalelos(Integer num){
        this.numberParalelos = num;
    }

    public Integer getNumberParalelos(){
        return this.numberParalelos;
    }

    public void setNumberStudentsByParalelo(Integer num){
        this.numberStudentsByParalelo = num;
    }

    public Integer getNumberStudentsByParalelo(){
        return this.numberStudentsByParalelo;
    }
    
    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Long getSemesterId(){
        return this.semesterId;
    }

    public void setSemesterId(Long id){
        this.semesterId = id;
    }

    
    public Assignature getAssignature() {
        return assignature;
    }

    public void setAssignature(Assignature assignature) {
        this.assignature = assignature;
    }

    public Long getAssignatureId(){
        return this.assignatureId;
    }

    public void setAssignatureId(Long id){
        this.assignatureId = id;
    }

    public Long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.Id = id;
	}

    private static final long serialVersionUID = 1L;
}
