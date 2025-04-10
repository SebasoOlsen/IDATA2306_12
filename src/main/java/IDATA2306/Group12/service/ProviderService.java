package IDATA2306.Group12.service;

import IDATA2306.Group12.dto.provider.ProviderDTO;
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

    public List<ProviderDTO> getAllProviders() {
        return providerRepository.findAll().stream()
                .map(providerMapper::toDTO)
                .toList();
    }

    public ProviderDTO getProviderById(int id) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        return providerMapper.toDTO(provider);
    }

    public ProviderDTO createProvider(ProviderDTO providerDTO) {
        Provider provider = providerMapper.toEntity(providerDTO);
        Provider saved = providerRepository.save(provider);
        return providerMapper.toDTO(saved);
    }

    @Transactional
    public ProviderDTO updateProvider(int id, ProviderDTO providerDTO) {
        Provider existingProvider = providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        existingProvider.setName(providerDTO.getName());

        Provider saved = providerRepository.save(existingProvider);
        return providerMapper.toDTO(saved);
    }

    @Transactional
    public void deleteProvider(int id) {
        providerRepository.deleteById(id);
    }

    public List<Provider> getProvidersByName(String name) {
        return providerRepository.findByName(name);
    }





//    @Autowired
//    private ProviderRepository providerRepository;
//
//    public List<ProviderDTO> getAllProviders() {
//        return providerRepository.findAll().stream()
//            .map(provider -> ProviderMapper.toDTO(provider)).toList();
//    }
//
//    public ProviderDTO getProviderById(int id) {
//        return ProviderMapper.toDTO(providerRepository.findById(id).orElse(null));
//    }
//
//    public ProviderDTO createProvider(ProviderDTO providerDTO) {
//        providerRepository.save(ProviderMapper.toEntity(providerDTO));
//        return providerDTO;
//    }
//
//    @Transactional
//    public ProviderDTO updateProvider(int id, ProviderDTO providerDTO) {
//        Provider provider = ProviderMapper.toEntity(providerDTO);
//        Provider existingProvider = providerRepository.findById(id).orElseThrow(() -> new RuntimeException("Provider not found"));
//        existingProvider.setId(provider.getId());
//        existingProvider.setName(provider.getName());
//        providerRepository.save(existingProvider);
//        return ProviderMapper.toDTO(existingProvider);
//    }
//
//    @Transactional
//    public void deleteProvider(int id) {
//        providerRepository.deleteById(id);
//    }
//
//    public List<Provider> getProvidersByName(String name) {
//        return providerRepository.findByName(name);
//    }

}
