package morena.example.doclist.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "address_street")),
        @AttributeOverride(name = "area", column = @Column(name = "address_area")),
        @AttributeOverride(name = "city", column = @Column(name = "address_city")),
        @AttributeOverride(name = "country", column = @Column(name = "address_country")),
        @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code"))
})
public class Address implements Serializable {
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

    public void setStreet(String street) {
        this.street = street;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(area, address.area) &&
                Objects.equals(city, address.city) &&
                Objects.equals(country, address.country) &&
                Objects.equals(postalCode, address.postalCode);
    }
}
