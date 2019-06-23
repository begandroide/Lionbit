package cl.lionbit.sga.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


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
    private String semesterTaught;

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public String getSemesterTaught() {
        return semesterTaught;
    }

    public void setSemesterTaught(String semesterTaught) {
        this.semesterTaught = semesterTaught;
    }

    private static final long serialVersionUID = 1L;
}
