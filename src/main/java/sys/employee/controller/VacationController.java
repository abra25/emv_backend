package sys.employee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sys.employee.entity.Status;
import sys.employee.entity.Vacation;
import sys.employee.service.VacationService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/evs/vacations")
public class VacationController {
    @Autowired
    public VacationService service;
    @GetMapping
    public List<Vacation> getAllVacation() {
        return service.getAllVacation();
    }

    @GetMapping("/{vcnId}")
    public Vacation getVacationById(@PathVariable Long vcnId) {
        return service.getVacationById(vcnId);
    }

    @GetMapping("/user/{id}")
    public List<Vacation> getByUserId(@PathVariable Long id) {
        return service.getVacationsByUserId(id);
    }

    // Get only pending vacations (for admin)
    @GetMapping("/pending")
    public List<Vacation> getPendingVacations() {
        return service.getPendingVacations();
    }

    // Submit vacation (defaults to PENDING)
    @PostMapping
    public Vacation postVacation(@RequestBody Vacation vacation) {
        vacation.setStatus(Status.pending); // default status
        return service.postVacation(vacation);
    }

    @PutMapping("/{vcnId}")
    public ResponseEntity<Vacation> updateVacation(@RequestBody Vacation vacation, @PathVariable Long vcnId) {
        Vacation updatedVacation = service.updateVacation(vacation, vcnId);
        return ResponseEntity.ok(updatedVacation);
    }

    //Update status only (approve/reject)
    @PutMapping("/{vcnId}/status")
    public ResponseEntity<Vacation> updateVacationStatus(@PathVariable Long vcnId,@RequestParam String status) {
        Vacation vacation = service.getVacationById(vcnId);
        if (vacation == null) {
            return ResponseEntity.notFound().build();
        }
        // Validate and set the correct enum
        switch (status.toLowerCase()) {
            case "approved":
                vacation.setStatus(Status.approved);
                break;
            case "rejected":
                vacation.setStatus(Status.rejected);
                break;
            case "pending":
                vacation.setStatus(Status.pending);
                break;
            default:
                return ResponseEntity.badRequest().build(); // Invalid status
        }
        Vacation updatedVacation = service.saveVacation(vacation);
        return ResponseEntity.ok(updatedVacation);
    }

    @DeleteMapping("/{vcnId}")
    public ResponseEntity<?> deleteVacation(@PathVariable Long vcnId) {
        service.deleteVacation(vcnId);
        return ResponseEntity.ok().build();
    }
}
