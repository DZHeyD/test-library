package kz.netcracker.testlibrary.domain.model.book;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    protected LocalDateTime updatedAt = LocalDateTime.now();

    @Column(nullable = false)
    protected Boolean deleted = Boolean.FALSE;

    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.deleted = true;
    }

    public void restore() {
        this.deleted = false;
    }
}
