package data.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "house", schema = "public", catalog = "traineedb")
public class HouseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "city", nullable = false, length = -1)
    private String city;
    @Basic
    @Column(name = "street", nullable = false, length = -1)
    private String street;
    @Basic
    @Column(name = "number", nullable = false)
    private int number;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<PersonEntity> persons = new HashSet<>();

    public HouseEntity(String city, String street, int number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public HouseEntity(int id, String city, String street, int number) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public HouseEntity() {

    }

    public Collection<PersonEntity> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonEntity> persons) {
        this.persons = persons;
    }

    public void addPerson(PersonEntity person) {
        person.setHouse(this);
        persons.add(person);
    }

    public void removePerson(PersonEntity person) {
        persons.remove(person);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        HouseEntity that = (HouseEntity) o;

        if (id != that.id)
            return false;
        if (number != that.number)
            return false;
        if (!Objects.equals(city, that.city))
            return false;
        if (!Objects.equals(street, that.street))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "г." + city + " ул." + street + " д." + number;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + number;
        return result;
    }
}
