package br.com.FindJobs.api.models;

import br.com.FindJobs.api.models.enums.StatusUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "enterprises")
@EntityListeners(AuditingEntityListener.class)
public class EnterpriseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(nullable = false, name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(length = 300)
    private String site;

    @Column(nullable = false)
    private Double note = 0.0D;

    @JsonIgnore
    @OneToMany(mappedBy = "enterprise")
    private List<VacancyModel> vancacies;

    @JsonIgnore
    @OneToMany(mappedBy = "enterprise")
    private List<CompanyReviewModel> companyReview;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserModel user;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public List<VacancyModel> getVancacies() {
        return vancacies;
    }

    public void setVancacies(List<VacancyModel> vancacies) {
        this.vancacies = vancacies;
    }

    public List<CompanyReviewModel> getCompanyReview() {
        return companyReview;
    }

    public void setCompanyReview(List<CompanyReviewModel> companyReview) {
        this.companyReview = companyReview;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
