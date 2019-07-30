package cl.lionbit.sga.entities;

import cl.lionbit.sga.constans.Semester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="teacher_has_courses")
@ApiModel(description = "This is a table resulting from a relationship many to many")
public class TeacherHasACourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Is the primary key, the database auto generate this ID")
    private Long courseID;

    @JoinColumn(name = "teacher_id",  referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Teacher.class, fetch = FetchType.EAGER)
    private Teacher teacher;

    @Column(name = "teacher_id")
    private Long teacherId;

    @JoinColumn(name = "assignature_id",  referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Assignature.class, fetch = FetchType.EAGER)
    private Assignature assignature;
    
    @Column(name = "assignature_id")
    private Long assignatureId;

    @NotNull
    @Column(name="semester_taught")
    @Enumerated(EnumType.STRING)
    private Semester semesterTaught;

    @Transient
    @Temporal(TemporalType.DATE)
    private Date year;

    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public Semester getSemesterTaught() {
        return semesterTaught;
    }

    public void setSemesterTaught(String semesterTaught) {
        Semester ns = Semester.valueOf(semesterTaught);
        this.semesterTaught = ns;
    }
    
    public void setSemesterTaught(Semester semesterTaught) {
        this.semesterTaught = semesterTaught;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public void setAssignature(Assignature assignature){
        this.assignature = assignature;
    }

    public Assignature getAssignature(){
        return assignature;
    }
    

    public void setAssignatureId(Long assignature){
        this.assignatureId = assignature;
    }

    public Long getAssignatureId(){
        return assignatureId;
    }

    
    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    public Teacher getTeacher(){
        return teacher;
    }

    public void setTeacherId(Long teacherId){
        this.teacherId = teacherId;
    }

    public Long getTeacherId(){
        return teacherId;
    }

    private static final long serialVersionUID = 1L;
}
