package repository;

public class User {
    final private Integer id;
    final private String name;
    final private String surname;
    final private String middleName;

    public User(String name, String surname, String middleName) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }

    public User(Integer id, String name, String surname, String middleName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + middleName;
    }
}
