package cl.lionbit.sga.entities;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="students_evaluations")
@ApiModel(description = "This is a table resulting from a relationship many to many")
public class StudentAndEvaluation implements Serializable {

    private Integer grade;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    private static final long serialVersionUID = 1L;
}
