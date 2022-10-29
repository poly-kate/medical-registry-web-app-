package medregistry.service;

import exception.PatientException;
import lombok.Getter;
import medregistry.entity.Patient;
import medregistry.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Getter
    private PatientRepository repository;

    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient add(Patient patient){
        if (repository.existsById(patient.getPolicyNumber())) {
            throw new PatientException("Запись уже существует");
        }
        return repository.save(patient);
    }

    public void deleteById(int id){
        if (!repository.existsById(id)){
            throw new PatientException("Запись не существует");
        }
        repository.deleteById(id);
    }

    public Object findAll() {
        return repository.findAll();
    }


    public Object searchIdAndSurname(String textSearch)
    {
        try{
            return findById(Integer.parseInt(textSearch));
        }
        catch (NumberFormatException  e){
            //не получилось преобразовать в int
            return repository.findBySurname(textSearch);
        }
        catch (PatientException e)
        {
            //нет пациента с данным номером
            return  null;
        }
    }


    public Patient findById(int id)
    {
        if (!repository.existsById(id)){
            throw new PatientException("Запись не существует");
        }
        return repository.findById(id).get();
    }

}
