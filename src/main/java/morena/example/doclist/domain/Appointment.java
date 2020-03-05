package morena.example.doclist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String patientName;

    @NotNull
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonIgnore
    private Doctor doctor;


    public Appointment() {
    }

    public Appointment(String patientName, Date date, Doctor doctor) {
        this.patientName = patientName;
        this.date = date;
        this.doctor = doctor;
    }

    public long getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public Date getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setId(long id) {
        this.id = id;
    }
}