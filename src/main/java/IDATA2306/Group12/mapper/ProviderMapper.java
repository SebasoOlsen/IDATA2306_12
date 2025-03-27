package IDATA2306.Group12.mapper;

import IDATA2306.Group12.entity.Provider;
import IDATA2306.Group12.dto.ProviderDTO;

/**
 * Mapper class for converting between Provider entity and ProviderDTO.
 */
public class ProviderMapper {

    /**
     * Converts a Provider entity to a ProviderDTO.
     *
     * @param provider the Provider entity to convert
     * @return the corresponding ProviderDTO
     */
    public static ProviderDTO toDTO(Provider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Provider cannot be null");
        }
        return new ProviderDTO(
            provider.getId(),
            provider.getName()
        );
    }

    /**
     * Converts a ProviderDTO to a Provider entity.
     *
     * @param providerDTO the ProviderDTO to convert
     * @return the corresponding Provider entity
     */
    public static Provider toEntity(ProviderDTO providerDTO) {
        if (providerDTO == null) {
            throw new IllegalArgumentException("ProviderDTO cannot be null");
        }
        Provider provider = new Provider();
        provider.setId(providerDTO.getId());
        provider.setName(providerDTO.getName());
        return provider;
    }
}