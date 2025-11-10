package sys.employee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sys.employee.entity.Vacation;

@Repository
public interface VacationRepo extends JpaRepository<Vacation,Long> {
     Vacation getVacationByVcnId(Long vcnId);
     List<Vacation> findByUserId(Long id);

}
