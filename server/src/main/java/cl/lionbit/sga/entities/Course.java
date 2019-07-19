package cl.lionbit.sga.entities;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="courses")
@ApiModel(description = "All details about the Course.")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseID;

    @Column(name="name_course", length = 20)
    private String nameCourse;

    @Column(name="code_usm", length = 6, unique = true)
    private String codeUsm;

    @Column(name="number_credits")
    private Integer numberCredits;

    @Column(name="description_course", length = 200)
    private String descriptionCourse;

    @Temporal(TemporalType.DATE)
    @Column(name="create_at")
    private Date createAt;

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getCodeUsm() {
        return codeUsm;
    }

    public void setCodeUsm(String codeUsm) {
        this.codeUsm = codeUsm;
    }

    public Integer getNumberCredits() {
        return numberCredits;
    }

    public void setNumberCredits(Integer numberCredits) {
        this.numberCredits = numberCredits;
    }

    public String getDescriptionCourse() {
        return descriptionCourse;
    }

    public void setDescriptionCourse(String descriptionCourse) {
        this.descriptionCourse = descriptionCourse;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


    private static final long serialVersionUID = 1L;
}
