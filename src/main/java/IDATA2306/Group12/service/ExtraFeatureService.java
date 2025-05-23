package IDATA2306.Group12.service;

import java.util.List;

import org.springframework.stereotype.Service;

import IDATA2306.Group12.entity.ExtraFeature;
import IDATA2306.Group12.repository.ExtraFeatureRepository;

/**
 * Service class for managing extra features.
 * Handles retrieval, creation, and saving of extra features.
 */
@Service
public class ExtraFeatureService {

    private final ExtraFeatureRepository repository;

    /**
     * Constructs an ExtraFeatureService with the required repository.
     *
     * @param repository the ExtraFeatureRepository
     */
    public ExtraFeatureService(ExtraFeatureRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves an ExtraFeature by name, or creates and saves it if not found.
     *
     * @param name the name of the extra feature
     * @return the found or newly created ExtraFeature
     */
    public ExtraFeature getOrCreateByName(String name) {
        return repository.findByName(name).stream().findFirst().orElseGet(() -> {
            ExtraFeature extraFeature = new ExtraFeature();
            extraFeature.setName(name);
            return repository.save(extraFeature);
        });
    }

    /**
     * Retrieves all extra features.
     *
     * @return a list of all ExtraFeature entities
     */
    public List<ExtraFeature> getAllFeatures() {
        return repository.findAll();
    }

    /**
     * Finds extra features by name.
     *
     * @param name the name to search for
     * @return a list of ExtraFeature entities with the given name
     */
    public List<ExtraFeature> findByName(String name) {
        return repository.findByName(name);
    }

    /**
     * Saves the given extra feature.
     *
     * @param feature the ExtraFeature to save
     * @return the saved ExtraFeature entity
     */
    public ExtraFeature save(ExtraFeature feature) {
        return repository.save(feature);
    }
}
