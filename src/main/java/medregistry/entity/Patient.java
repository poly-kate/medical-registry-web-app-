package medregistry.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient{

    @NotBlank(message = "Обязательное поле!")
    @Getter
    @Setter
    @Size(min = 1, max = 20, message = "Не менее 1 и не более 20 символов")
    @Column(nullable = false)
    private String surname;

    @NotBlank(message = "Обязательное поле!")
    @Getter
    @Setter
    @Size(min = 1, max = 20, message = "Не менее 1 и не более 20 символов")
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    private String patronymic;


    @NotNull(message = "Укажите дату рождения!")
    @Past(message = "Укажите дату в прошлом!")
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate birth;


    @NotNull(message = "Укажите номер полиса!")
    @Getter
    @Setter
    @Range(min = 10000, max = 99999, message = "Неверный формат!")
    @Column(nullable = false)
    @Id
    private int policyNumber;

    @Getter
    @Setter
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList = new ArrayList<>();

    @Override
    public String toString() {
        return (surname + " " + name.charAt(0) + ". "
                + patronymic.charAt(0) + ".(" + policyNumber + ")");
    }
}
