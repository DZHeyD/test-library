package kz.netcracker.testlibrary.domain.model.book;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Builder.Default
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    protected LocalDateTime updatedAt = LocalDateTime.now();

    @Builder.Default
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
