package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.provider.ProviderResponseDTO;
import IDATA2306.Group12.entity.Provider;
import IDATA2306.Group12.mapper.ProviderMapper;
import IDATA2306.Group12.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for managing providers.
 * Handles creation, retrieval, updating, and deletion of providers.
 */
@Service
public class ProviderService {


    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    /**
     * Constructs a ProviderService with required dependencies.
     *
     * @param providerRepository the provider repository
     * @param providerMapper the provider mapper
     */
    public ProviderService(ProviderRepository providerRepository, ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
    }

    /**
     * Retrieves all providers.
     *
     * @return a list of ProviderResponseDTOs
     */
    public List<ProviderResponseDTO> getAllProviders() {
        return providerRepository.findAll().stream()
                .map(providerMapper::toResponseDTO)
                .toList();
    }

    /**
     * Retrieves a provider by its ID.
     *
     * @param id the provider ID
     * @return the ProviderResponseDTO
     * @throws IllegalArgumentException if the provider is not found
     */
    public ProviderResponseDTO getProviderById(int id) throws IllegalArgumentException{
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));
        return providerMapper.toResponseDTO(provider);
    }

    /**
     * Creates a new provider.
     *
     * @param providerDTO the provider DTO
     * @return the created ProviderResponseDTO
     */
    public ProviderResponseDTO createProvider(ProviderResponseDTO providerDTO) {
        Provider provider = providerMapper.toEntity(providerDTO);
        Provider saved = providerRepository.save(provider);
        return providerMapper.toResponseDTO(saved);
    }

    /**
     * Updates an existing provider.
     *
     * @param id the provider ID
     * @param providerDTO the provider DTO with updated data
     * @return the updated ProviderResponseDTO
     * @throws IllegalArgumentException if the provider is not found
     */
    @Transactional
    public ProviderResponseDTO updateProvider(int id, ProviderResponseDTO providerDTO) throws IllegalArgumentException{
        Provider existingProvider = providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));

        existingProvider.setName(providerDTO.getName());

        Provider saved = providerRepository.save(existingProvider);
        return providerMapper.toResponseDTO(saved);
    }

    /**
     * Deletes a provider by its ID.
     *
     * @param id the provider ID
     */
    @Transactional
    public void deleteProvider(int id) {
        providerRepository.deleteById(id);
    }

    public List<Provider> getProvidersByName(String name) {
        return providerRepository.findByName(name);
    }

}
