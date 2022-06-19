package models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRes {
    @JsonProperty("data")
    private String id;

    private String name;

    private String job;

    private String createdAt;

    private String updatedAt;

    private String email;
}
