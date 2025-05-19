package IDATA2306.Group12.service;

import java.util.List;

import org.springframework.stereotype.Service;

import IDATA2306.Group12.entity.ExtraFeature;
import IDATA2306.Group12.repository.ExtraFeatureRepository;

@Service
public class ExtraFeatureService {

    private final ExtraFeatureRepository repository;

    public ExtraFeatureService(ExtraFeatureRepository repository) {
        this.repository = repository;
    }

    public ExtraFeature getOrCreateByName(String name) {
        return repository.findByName(name).stream().findFirst().orElseGet(() -> {
            ExtraFeature extraFeature = new ExtraFeature();
            extraFeature.setName(name);
            return repository.save(extraFeature);
        });
    }

    public List<ExtraFeature> getAllFeatures() {
        return repository.findAll();
    }

    public List<ExtraFeature> findByName(String name) {
        return repository.findByName(name);
    }

    public ExtraFeature save(ExtraFeature feature) {
        return repository.save(feature);
    }
}
