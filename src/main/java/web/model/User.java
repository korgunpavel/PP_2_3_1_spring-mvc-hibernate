package web.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    @NotEmpty(message = "First name should be not empty")
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters")
    @Pattern(regexp = "^\\p{Upper}\\p{Lower}+",
            message = "First name must begin with a capital letter and consist only of letters.")
    private String firstName;

    @Column(name = "lastName")
    @NotEmpty(message = "Last name should be not empty")
    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 characters")
    @Pattern(regexp = "^\\p{Upper}\\p{Lower}+",
            message = "Last name must begin with a capital letter and consist only of letters.")
    private String lastName;

    @Column(name = "age")
    @Min(value = 0, message = "Age must be greater than 0")
    private int age;

    public User() {
    }

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " ," + age;
    }
}
