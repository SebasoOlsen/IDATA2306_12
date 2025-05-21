package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.provider.ProviderResponseDTO;
import IDATA2306.Group12.entity.Provider;
import IDATA2306.Group12.mapper.ProviderMapper;
import IDATA2306.Group12.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProviderService {


    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    public ProviderService(ProviderRepository providerRepository, ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
    }

    public List<ProviderResponseDTO> getAllProviders() {
        return providerRepository.findAll().stream()
                .map(providerMapper::toResponseDTO)
                .toList();
    }

    public ProviderResponseDTO getProviderById(int id) throws IllegalArgumentException{
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));
        return providerMapper.toResponseDTO(provider);
    }

    public ProviderResponseDTO createProvider(ProviderResponseDTO providerDTO) {
        Provider provider = providerMapper.toEntity(providerDTO);
        Provider saved = providerRepository.save(provider);
        return providerMapper.toResponseDTO(saved);
    }

    @Transactional
    public ProviderResponseDTO updateProvider(int id, ProviderResponseDTO providerDTO) throws IllegalArgumentException{
        Provider existingProvider = providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));

        existingProvider.setName(providerDTO.getName());

        Provider saved = providerRepository.save(existingProvider);
        return providerMapper.toResponseDTO(saved);
    }

    @Transactional
    public void deleteProvider(int id) {
        providerRepository.deleteById(id);
    }

    public List<Provider> getProvidersByName(String name) {
        return providerRepository.findByName(name);
    }

}
