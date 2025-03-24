package IDATA2306.Group12.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for Provider entity.
 */
public class ProviderDTO {

    private int id;

    @NotBlank(message = "Provider name cannot be empty")
    private String name;

    public ProviderDTO() {}

    public ProviderDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
