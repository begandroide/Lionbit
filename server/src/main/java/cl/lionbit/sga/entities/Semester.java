package cl.lionbit.sga.entities;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="semester")
@ApiModel(description = "Semester and year.")
public class Semester implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long semesterID;

    @Column(name="number", length = 1)
    private Integer numberSemester;

    @Column(name="year")
    private String yearSemester;

    @Temporal(TemporalType.DATE)
    @Column(name="create_at")
    private Date createAt;

    public Long getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(Long semesterID) {
        this.semesterID = semesterID;
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


    private static final long serialVersionUID = 1L;
}
