package medregistry.repositories;

import medregistry.entity.Doctor;
import medregistry.entity.Patient;
import medregistry.enumer.TypeDoctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends PagingAndSortingRepository<Doctor, Integer> {

    @Query(value = "SELECT c FROM Doctor c WHERE (c.typeDoctor) LIKE %:typeDoctor%")
    List<Doctor> findByType(@Param("typeDoctor") int typeDoctor);
}
