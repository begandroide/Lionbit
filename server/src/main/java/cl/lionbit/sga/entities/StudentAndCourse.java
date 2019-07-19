package cl.lionbit.sga.entities;

import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Table(name="students_courses")
@ApiModel(description = "This is a table resulting from a relationship many to many")
public class StudentAndCourse implements Serializable {


    private boolean attendance;

    @Column(name="day_attendance")
    private Date dayAttendance;

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public Date getDayAttendance() {
        return dayAttendance;
    }

    public void setDayAttendance(Date dayAttendance) {
        this.dayAttendance = dayAttendance;
    }

    private static final long serialVersionUID = 1L;
}
