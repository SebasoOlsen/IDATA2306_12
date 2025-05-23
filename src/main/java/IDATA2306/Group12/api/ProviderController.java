package IDATA2306.Group12.api;

import IDATA2306.Group12.dto.provider.ProviderResponseDTO;
import IDATA2306.Group12.service.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller for handling provider-related API requests.
 */
@RestController
@RequestMapping("/api/providers")
@Tag(name = "Provider Management", description = "APIs for managing providers.")
public class ProviderController {

    private final ProviderService providerService;

    /**
     * Constructor for ProviderController.
     * @param providerService the provider service
     */
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }
    /**
     * Get a list of all providers.
     * @return list of all providers
     */
    @Operation(
            summary = "Get a list of all providers.",
            description = "Get a list of all providers."
    )
    @GetMapping("/admin/allProviders")
    @ApiResponse(responseCode = "200", description = "List of all providers.")
    @ApiResponse(responseCode = "403", description = "Not authorized to view this list of providers.")
    public ResponseEntity<List<ProviderResponseDTO>> getAllProviders() {
            return ResponseEntity.ok(providerService.getAllProviders());
    }
    /**
     * Get a provider by ID.
     * @param id the provider ID
     * @return provider with the matching ID
     */
    @Operation(
            summary = "Get a provider by ID.",
            description = "Get a provider by ID."
    )
    @ApiResponse(responseCode = "200", description = "Provider with the matching ID.")
    @ApiResponse(responseCode = "404", description = "Provider not found.")
    @ApiResponse(responseCode = "403", description = "Not authorized to view this provider.")
    @GetMapping("public/search/{id}")
    public ResponseEntity<ProviderResponseDTO> getProviderById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(providerService.getProviderById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * Create a new provider.
     * @param providerDTO the provider data
     * @return the created provider
     */
    @Operation(
            summary = "Create a new provider.",
            description = "Create a new provider using a ProviderResponseDTO."
    )
    @PostMapping("/admin/createProvider")
    @ApiResponse(responseCode = "201", description = "Provider created successfully.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    @ApiResponse(responseCode = "403", description = "Not authorized to create a provider.")
    public ResponseEntity<ProviderResponseDTO> createProvider(@Valid @RequestBody ProviderResponseDTO providerDTO) {
        try {
            return ResponseEntity.ok(providerService.createProvider(providerDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     * Update an existing provider.
     * @param id the provider ID
     * @param providerDTO the updated provider data
     * @return the updated provider
     */
    @Operation(
            summary = "Update an existing provider",
            description = "Update an existing provider using a ProviderResponseDTO."
    )
    @ApiResponse(responseCode = "200", description = "Provider updated successfully.")
    @ApiResponse(responseCode = "404", description = "Provider not found.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    @ApiResponse(responseCode = "403", description = "Not authorized to update this provider.")
    @PutMapping("/admin/updateProvider/{id}")
    public ResponseEntity<ProviderResponseDTO> updateProvider(@PathVariable int id, @Valid @RequestBody ProviderResponseDTO providerDTO) {
        try {
            return ResponseEntity.ok(providerService.updateProvider(id, providerDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * Delete an existing provider.
     * @param id the provider ID
     * @return status message
     */
    @Operation(
            summary = "Delete an existing provider",
            description = "Delete a provider using the provider ID."
    )
    @ApiResponse(responseCode = "204", description = "Provider deleted successfully.")
    @ApiResponse(responseCode = "403", description = "Not authorized to delete this provider.")
    @DeleteMapping("/admin/deleteProvider/{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable int id) {
        providerService.deleteProvider(id);
        return ResponseEntity.status(204).body("Provider deleted successfully.");
    }
}