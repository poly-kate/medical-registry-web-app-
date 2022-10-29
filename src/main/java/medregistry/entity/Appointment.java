package medregistry.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Appointment extends Identify{

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@NotNull(message = "Укажите пациента!")
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(nullable = false)
    Patient patient;

    @NotNull(message = "Укажите доктора!")
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(nullable = false)
    Doctor doctor;

    //дату у время проверить!!!
    @NotNull(message = "Укажите дату записи!")
    //@Future(message = "Укажите дату в будущем!")
    @Getter
    @Setter
    //@DateTimeFormat(pattern = "yyyy-MM-dd ")
    @Column(nullable = false)
    private String dateAppointment;


    @NotNull(message = "Укажите время записи!")
    @Getter
    @Setter
    //@DateTimeFormat(pattern = "hh:mm")
    @Column(nullable = false)
    private String timeAppointment;
}
