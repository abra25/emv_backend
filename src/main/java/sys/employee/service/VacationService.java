package sys.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.employee.entity.Status;
import sys.employee.entity.Vacation;
import sys.employee.repo.VacationRepo;

import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VacationService {
    @Autowired
    private VacationRepo repo;
    public List<Vacation> getAllVacation() {
        return repo.findAll();
    }

    public Vacation getVacationById(Long vcnId) {
        return repo.getVacationByVcnId(vcnId);
    }

    public List<Vacation> getVacationsByUserId(Long id) {
        return repo.findByUserId(id);
    }

    public List<Vacation> getPendingVacations() {
        return repo.findAll().stream()
                .filter(v -> v.getStatus() == Status.pending)
                .collect(Collectors.toList());
    }

    public Vacation postVacation(Vacation vacation) {
        if (vacation.getVcnId() != null && repo.existsById(vacation.getVcnId())) {
            // Update logic
            return repo.save(vacation);
        } else {
            vacation.setVcnId(null); // Ensure it's treated as new
            return repo.save(vacation);
        }
    }

    public Vacation saveVacation(Vacation vacation) {
        return repo.save(vacation);  // this avoids detached entity issues
    }

    public Vacation updateVacation(Vacation vacation, Long vcnId) {
        Vacation existing = repo.findById(vcnId).orElseThrow();
        existing.setStartDate(vacation.getStartDate());
        existing.setEndDate(vacation.getEndDate());
        existing.setReason(vacation.getReason());
        existing.setStatus(vacation.getStatus());
        return repo.save(existing);
    }

    public Optional<Vacation> updateVacationStatus(Long vcnId, String status) {
        Optional<Vacation> vacationOpt = repo.findById(vcnId);
        if (vacationOpt.isPresent()) {
            Vacation vacation = vacationOpt.get();
            vacation.setStatus(Status.valueOf(status.toUpperCase()));
            return Optional.of(repo.save(vacation));
        }
        return Optional.empty();
    }

    public void deleteVacation(Long vcnId) {
        repo.deleteById(vcnId);
    }
}
