package morena.example.doclist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "practice_id", nullable = false)
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
}
