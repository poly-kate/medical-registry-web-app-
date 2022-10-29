package medregistry.repositories;

import medregistry.entity.Appointment;
import medregistry.entity.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppointmentRepository extends PagingAndSortingRepository<Appointment, Integer> {
}
