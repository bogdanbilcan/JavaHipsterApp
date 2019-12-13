package com.bbc.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bbc.myapp.domain.NotifTemplate} entity.
 */
public class NotifTemplateDTO implements Serializable {

    private Long id;

    @NotNull
    private String emailAddresses;

    @Size(max = 600)
    private String message;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(String emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotifTemplateDTO notifTemplateDTO = (NotifTemplateDTO) o;
        if (notifTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notifTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotifTemplateDTO{" +
            "id=" + getId() +
            ", emailAddresses='" + getEmailAddresses() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
