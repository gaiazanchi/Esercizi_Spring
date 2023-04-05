package co.develhope.Email02.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private String contactId;
    private String title;
    private String text;
}
