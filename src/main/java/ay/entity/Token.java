package ay.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Token implements Serializable {

    @Column(unique = true)
    private String value;
    @Id
    @GeneratedValue
    private Long id;

    public Token() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "value : " + value;
    }
}
