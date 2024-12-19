package com.td.notification_serivce.repository;

import com.td.notification_serivce.entity.NotificationTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplateEntity,Long> {
}
