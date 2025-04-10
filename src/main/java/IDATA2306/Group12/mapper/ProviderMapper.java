package IDATA2306.Group12.mapper;

import org.springframework.stereotype.Component;

import IDATA2306.Group12.dto.provider.ProviderDTO;
import IDATA2306.Group12.entity.Provider;

/**
 * Mapper class for converting between Provider entity and ProviderDTO.
 */
@Component
public class ProviderMapper {

    public ProviderMapper () {}

    /**
     * Converts a Provider entity to a ProviderDTO.
     *
     * @param provider the Provider entity to convert
     * @return the corresponding ProviderDTO
     */
    public ProviderDTO toDTO(Provider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Provider cannot be null");
        }
        ProviderDTO dto = new ProviderDTO();
        dto.setId(provider.getId());
        dto.setName(provider.getName());
        return dto;
    }

    /**
     * Converts a ProviderDTO to a Provider entity.
     *
     * @param providerDTO the ProviderDTO to convert
     * @return the corresponding Provider entity
     */
    public Provider toEntity(ProviderDTO providerDTO) {
        if (providerDTO == null) {
            throw new IllegalArgumentException("ProviderDTO cannot be null");
        }
        Provider provider = new Provider();
        provider.setId(providerDTO.getId());
        provider.setName(providerDTO.getName());
        return provider;
    }
}