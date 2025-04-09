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
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, unique = true, nullable = false)
    private String email;

    @Column(length = 300, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private StatusUser role = StatusUser.candidate;

    @Column(length = 500, name="refresh_token")
    private String refreshToken;

    @Column(name = "is_adm")
    private Boolean isAdm = false;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private EnterpriseModel enterprise;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ApplicationModel> applications;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CategoryModel> categories;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CompanyReviewModel> companyReview;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CommentModel> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<FavoriteModel> favorite;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<JobTypeModel> jobType;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private AddressUserModel address;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private ResumeModel resume;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusUser getRole() {
        return role;
    }

    public void setRole(StatusUser role) {
        this.role = role;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Boolean getAdm() {
        return isAdm;
    }

    public void setAdm(Boolean adm) {
        isAdm = adm;
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

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
    }

    public List<CompanyReviewModel> getCompanyReview() {
        return companyReview;
    }

    public void setCompanyReview(List<CompanyReviewModel> companyReview) {
        this.companyReview = companyReview;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public List<FavoriteModel> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<FavoriteModel> favorite) {
        this.favorite = favorite;
    }

    public List<JobTypeModel> getJobType() {
        return jobType;
    }

    public void setJobType(List<JobTypeModel> jobType) {
        this.jobType = jobType;
    }

    public AddressUserModel getAddress() {
        return address;
    }

    public void setAddress(AddressUserModel address) {
        this.address = address;
    }

    public ResumeModel getResume() {
        return resume;
    }

    public void setResume(ResumeModel resume) {
        this.resume = resume;
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
