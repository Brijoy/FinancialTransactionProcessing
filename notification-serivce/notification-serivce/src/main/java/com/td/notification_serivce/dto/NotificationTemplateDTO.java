package com.td.notification_serivce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationTemplateDTO {
    private Long id;
    private String templateName;
    private String templateContent;

}
