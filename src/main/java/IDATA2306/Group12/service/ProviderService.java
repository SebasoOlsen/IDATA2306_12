package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.ProviderDTO;
import IDATA2306.Group12.entity.Provider;
import IDATA2306.Group12.mapper.ProviderMapper;
import IDATA2306.Group12.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public List<ProviderDTO> getAllProviders() {
        return providerRepository.findAll().stream()
            .map(provider -> ProviderMapper.toDTO(provider)).toList();
    }

    public ProviderDTO getProviderById(int id) {
        return ProviderMapper.toDTO(providerRepository.findById(id).orElse(null));
    }

    public ProviderDTO createProvider(ProviderDTO providerDTO) {
        providerRepository.save(ProviderMapper.toEntity(providerDTO));
        return providerDTO;
    }

    @Transactional
    public ProviderDTO updateProvider(int id, ProviderDTO providerDTO) {
        Provider provider = ProviderMapper.toEntity(providerDTO);
        Provider existingProvider = providerRepository.findById(id).orElseThrow(() -> new RuntimeException("Provider not found"));
        existingProvider.setId(provider.getId());
        existingProvider.setName(provider.getName());
        providerRepository.save(existingProvider);
        return ProviderMapper.toDTO(existingProvider);
    }

    @Transactional
    public void deleteProvider(int id) {
        providerRepository.deleteById(id);
    }

    public List<Provider> getProvidersByName(String name) {
        return providerRepository.findByName(name);
    }

}
