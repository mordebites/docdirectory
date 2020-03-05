package morena.example.doclist.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "practices")
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    public Practice() {}

    public Practice(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
