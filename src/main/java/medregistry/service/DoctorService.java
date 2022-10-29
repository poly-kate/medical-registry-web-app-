package medregistry.service;

import exception.DoctorException;
import exception.PatientException;
import lombok.Getter;
import medregistry.entity.Doctor;
import medregistry.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Getter
    private DoctorRepository repository;

    @Autowired
    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public Doctor add(Doctor doctor) {
        if (repository.existsById(doctor.getId())) {
            throw new DoctorException("Запись уже существует");
        }
        return repository.save(doctor);
    }

    public void deleteById(int id){
        if (!repository.existsById(id)){
            throw new DoctorException("Запись не существует");
        }
        repository.deleteById(id);

    }

    public Object findAll() {
        return repository.findAll();
    }


/* list
    public Object findByType(int typeDoctor)
    {
        return repository.findByType(typeDoctor);
    }
*/

}
