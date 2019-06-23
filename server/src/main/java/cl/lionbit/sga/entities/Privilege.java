package cl.lionbit.sga.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="role_privileges")
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long PrivilegeID;

    @Column(name = "name_privilege")
    private String namePrivilege;

    public Long getId() {
        return PrivilegeID;
    }

    public void setId(Long id) {
        this.PrivilegeID = id;
    }

    public String getName() {
        return namePrivilege;
    }

    public void setName(String name) {
        this.namePrivilege = name;
    }

    private static final long serialVersionUID = 1L;
}
