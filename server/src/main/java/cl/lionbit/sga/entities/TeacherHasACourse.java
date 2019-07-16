package cl.lionbit.sga.entities;

import cl.lionbit.sga.constans.Semester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
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

    private static final long serialVersionUID = 1L;
}
