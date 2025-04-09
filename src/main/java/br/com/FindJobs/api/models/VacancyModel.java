package br.com.FindJobs.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vacancies")
@EntityListeners(AuditingEntityListener.class)
public class VacancyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, nullable = false)
    private String title;

    @Column(length = 3000, nullable = false)
    private String description;

    @Column(length = 300, nullable = false, name="type_contraction")
    private String typeContraction;

    @Column(length = 300, nullable = false)
    private String locate;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false, length = 150)
    private Integer total = 1;

    @Column(nullable = false)
    private Boolean status = true;

    @Column(nullable = false, name = "numeric_application")
    private Integer numericApplication;

    @Column(length = 300)
    private String category;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private EnterpriseModel enterprise;

    @JsonIgnore
    @OneToMany(mappedBy = "vacancy")
    private List<ApplicationModel> applications;

    @JsonIgnore
    @OneToMany(mappedBy = "vacancy")
    private List<FavoriteModel> favorite;

    @JsonIgnore
    @OneToMany(mappedBy = "vacancy")
    private List<CommentModel> comments;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeContraction() {
        return typeContraction;
    }

    public void setTypeContraction(String typeContraction) {
        this.typeContraction = typeContraction;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getNumericApplication() {
        return numericApplication;
    }

    public void setNumericApplication(Integer numericApplication) {
        this.numericApplication = numericApplication;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public EnterpriseModel getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseModel enterprise) {
        this.enterprise = enterprise;
    }

    public List<ApplicationModel> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationModel> applications) {
        this.applications = applications;
    }

    public List<FavoriteModel> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<FavoriteModel> favorite) {
        this.favorite = favorite;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
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
