package cl.lionbit.sga.entities;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="semester")
@ApiModel(description = "Semester and year.")
public class Semester implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name="number", length = 1)
    private Integer numberSemester;

    @Column(name="year")
    private String yearSemester;

    @Column(name="in_course")
    private Boolean inCourse;
    
    @Column(name="active")
    private Boolean active;

    @Temporal(TemporalType.DATE)
    @Column(name="create_at")
    private Date createAt;

    @OneToMany(cascade=CascadeType.REMOVE, mappedBy="semester")
	public Set<AssignatureAndSemester> courses;

    public Long getId() {
        return Id;
    }

    public void setId(Long semesterID) {
        this.Id = semesterID;
    }

    public Integer getNumberSemester() {
        return numberSemester;
    }

    public void setNumberSemester(Integer numberSemester) {
        this.numberSemester = numberSemester;
    }

    public String getYearSemester() {
        return yearSemester;
    }

    public void setYearSemester(String yearSemester) {
        this.yearSemester = yearSemester;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Boolean getInCourse() {
        return inCourse;
    }

    public void setInCourse(Boolean inCourse) {
        this.inCourse = inCourse;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    private static final long serialVersionUID = 1L;
}
