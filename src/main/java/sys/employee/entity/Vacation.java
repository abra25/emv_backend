package sys.employee.entity;

import jakarta.persistence.*;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Vacation_Request")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vcnId;
    private String startDate;
    private String endDate;
    private String reason;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.pending;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties(value = {"username","password","phone","role","email","gender","vacations"})
    private User user;
    public Vacation(){}

    public Vacation(Long vcnId, String startDate, String endDate, String reason, Status status, User user) {
        this.vcnId = vcnId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.user = user;
    }

    public Long getVcnId() {
        return vcnId;
    }

    public void setVcnId(Long vcnId) {
        this.vcnId = vcnId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
