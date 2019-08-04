package cl.lionbit.sga.entities;

// import cl.lionbit.sga.constans.Semester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

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

    @JoinColumn(name = "assignature_semester_id",  referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private AssignatureAndSemester assignatureAndSemester;
    
    @Column(name = "assignature_semester_id")
    private Long assignatureSemesterId;

    @Column(name = "paralelo")
    private Integer numberParalelo;

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

    public AssignatureAndSemester getAssignatureAndSemester() {
        return assignatureAndSemester;
    }

    public void setAssignatureAndSemester(AssignatureAndSemester assignatureAndSemester){
        this.assignatureAndSemester = assignatureAndSemester;
    }

    public Long getAssignatureAndSemesterId(){
        return this.assignatureSemesterId;
    }

    public void setAssignatureAndSemesterId(Long id){
        this.assignatureSemesterId = id;
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

    public void setNumberParalelo(Integer num){
        this.numberParalelo = num;
    }

    public Integer getNumberParalelo(){
        return this.numberParalelo;
    }

    private static final long serialVersionUID = 1L;
}
