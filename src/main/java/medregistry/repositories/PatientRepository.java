package medregistry.repositories;

import medregistry.entity.Doctor;
import medregistry.entity.Patient;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends PagingAndSortingRepository<Patient, Integer> {
    @Query(value = "SELECT c FROM Patient c WHERE (c.surname) LIKE %:surname%")
    List<Patient> findBySurname(@Param("surname") String surname);
}
