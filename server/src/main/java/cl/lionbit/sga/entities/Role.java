package cl.lionbit.sga.entities;

import cl.lionbit.sga.constans.Roles;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="roles_users")
@ApiModel(description = "This a role management table")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Is the primary key, the database auto generate this ID")
    private Integer rolID;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public Integer getRolID() {
        return rolID;
    }

    public void setRolID(Integer rolID) {
        this.rolID = rolID;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles rolName) {
        this.role = rolName;
    }

    private static final long serialVersionUID = 1L;
}
