package server.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class AuditingListener {

    @PrePersist
    public void setCreationDate(Object entity) {
        if (entity instanceof Auditable) {
            ((Auditable) entity).setCreatedAt(new Date());
            ((Auditable) entity).setUpdatedAt(new Date());
        }
    }

    @PreUpdate
    public void setModificationDate(Object entity) {
        if (entity instanceof Auditable) {
            ((Auditable) entity).setUpdatedAt(new Date());
        }
    }
}
