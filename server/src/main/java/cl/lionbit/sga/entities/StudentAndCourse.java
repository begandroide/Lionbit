package cl.lionbit.sga.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="students_courses")
@ApiModel(description = "This is a table resulting from a relationship many to many")
public class StudentAndCourse implements Serializable {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated teacher ID")
    private Long assignatureAndCourseID;

    @JoinColumn(name = "student_id",  referencedColumnName = "studentID", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    private Student student;

    @Column(name = "student_id")
    private Long studentId;
    
    @JoinColumn(name = "teacher_has_course_id",  referencedColumnName = "courseID", insertable = false, updatable = false)
    @ManyToOne(targetEntity = TeacherHasACourse.class, fetch = FetchType.EAGER)
    private TeacherHasACourse teacherHasACourse;

    @Column(name = "teacher_has_course_id")
    private Long teacherHasACourseId;

    public Student getStudent(){
        return this.student;
    }
    public void setStudent(Student student){
        this.student = student;
    }
    
    public Long getStudentId(){
        return this.studentId;
    }
    public void setStudentId(Long studentId){
        this.studentId = studentId;
    }
    
    public TeacherHasACourse getTeacherHasACourse(){
        return this.teacherHasACourse;
    }

    public void setTeacherHasACourse(TeacherHasACourse teacherHasACourse){
        this.teacherHasACourse = teacherHasACourse;
    }
    
    public Long getTeacherHasACourseId(){
        return this.teacherHasACourseId;
    }

    public void setTeacherHasACourseId(Long teacherHasACourseId){
        this.teacherHasACourseId = teacherHasACourseId;
    }
    
    public Long getAssignatureAndCourseID() {
		return assignatureAndCourseID;
	}

	/**
	 * @param id the id to set
	 */
	public void setAssignatureAndCourseID(Long id) {
		this.assignatureAndCourseID = id;
	}

    private static final long serialVersionUID = 1L;
}
