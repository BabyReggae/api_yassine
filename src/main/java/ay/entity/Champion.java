package ay.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Champion implements Serializable {

    @Column(unique = true)
    private String name;
    @Id
    @GeneratedValue
    private Long id;


    public Champion(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "name:" + name;
    }
}
