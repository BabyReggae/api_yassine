package ay.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER )
    @JoinColumn(name = "token_id", referencedColumnName = "id")
    private Token token;

    @Column
    private String pwd;

    @Column
    private String firstName;

    @Column
    private String lastname;

    @Id
    @GeneratedValue
    private Long id;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }


    public Long getId() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setpwd(String pwd) {
        this.pwd = pwd;
    }

    public Token getToken() {
        return token;
    }

    public void settoken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "first : " + firstName;
    }
}
