package morena.example.doclist.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@AttributeOverrides({
        @AttributeOverride( name = "street", column = @Column(name = "address_street")),
        @AttributeOverride( name = "area", column = @Column(name = "address_area")),
        @AttributeOverride( name = "city", column = @Column(name = "address_city")),
        @AttributeOverride( name = "country", column = @Column(name = "address_country")),
        @AttributeOverride( name = "postalCode", column = @Column(name = "address_postal_code"))
})
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String street;
    @NotNull
    private String area;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private String postalCode;

    public Address() {
    }

    public Address(String street, String area, String city, String country, String postalCode) {
        this.street = street;
        this.area = area;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
