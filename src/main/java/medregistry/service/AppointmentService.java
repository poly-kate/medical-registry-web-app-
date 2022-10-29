package medregistry.service;

import exception.AppointmentException;
import lombok.Getter;
import medregistry.entity.Appointment;
import medregistry.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Getter
    private AppointmentRepository repository;

    @Autowired
    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Appointment add( Appointment appointment){
        if (repository.existsById(appointment.getId())) {
            throw new AppointmentException("Запись уже существует");
        }
        return repository.save(appointment);
    }

    public void deleteById(int id){
        if (!repository.existsById(id)){
            throw new AppointmentException("Запись не существует");
        }
        repository.deleteById(id);
    }

    public Object findAll() {
        return repository.findAll();
    }



}
