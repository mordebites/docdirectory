package morena.example.doclist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "doctors")
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;
    @NotNull
    private String language; //enum?
    @NotNull
    private String type;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "practice_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Practice practice;

    public Doctor() {
    }

    public Doctor(String name, String language, String type, Practice practice) {
        this.name = name;
        this.language = language;
        this.type = type;
        this.practice = practice;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public String getType() {
        return type;
    }

    public Practice getPractice() {
        return practice;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", type='" + type + '\'' +
                ", practice=" + practice +
                '}';
    }
}
