package IDATA2306.Group12.service;

import IDATA2306.Group12.entity.Provider;
import IDATA2306.Group12.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }
    public Provider getProviderById(int id) {
        return providerRepository.findById(id).orElse(null);
    }
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }
    @Transactional
    public Provider updateProvider(int id, Provider provider) {
        Provider existingProvider = providerRepository.findById(id).orElseThrow(() -> new RuntimeException("Provider not found"));
        existingProvider.setId(provider.getId());
        existingProvider.setName(provider.getName());
        return providerRepository.save(existingProvider);
    }
    @Transactional
    public void deleteProvider(int id) {
        providerRepository.deleteById(id);
    }
    public List<Provider> getProvidersByName(String name) {
        return providerRepository.findByName(name);
    }

}
