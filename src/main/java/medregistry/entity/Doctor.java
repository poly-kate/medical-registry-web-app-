package medregistry.entity;

import lombok.Getter;
import lombok.Setter;
import medregistry.enumer.TypeDoctor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor extends Identify{

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    //@NotBlank(message = "Обязательное поле!")
    @Getter
    @Setter
    @Column(nullable = false)
    private TypeDoctor typeDoctor;

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

    @Getter
    @Setter
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentWeek = new ArrayList<>();

    @Override
    public String toString() {
        return (surname); /*+ " " + name.charAt(0) + ". "
                + patronymic.charAt(0) + ".(" + typeDoctor.getDisplayValue() + ")");*/
    }
}