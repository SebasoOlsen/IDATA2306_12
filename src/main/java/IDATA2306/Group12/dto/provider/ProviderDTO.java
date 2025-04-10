package IDATA2306.Group12.dto.provider;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for Provider entity.
 */
public class ProviderDTO {
    private int id;
    private String name;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

