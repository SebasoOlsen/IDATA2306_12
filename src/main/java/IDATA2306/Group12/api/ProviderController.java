package IDATA2306.Group12.api;

import IDATA2306.Group12.dto.provider.ProviderResponseDTO;
import IDATA2306.Group12.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping
    public List<ProviderResponseDTO> getAllproviders(){
        try{
            return providerService.getAllProviders();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    @GetMapping("/{id}")
    public ProviderResponseDTO getProviderById(@PathVariable int id){
        return providerService.getProviderById(id);
    }
    @PostMapping
    public ProviderResponseDTO createProvider(@RequestBody ProviderResponseDTO providerDTO){
        return providerService.createProvider(providerDTO);
    }
    @PutMapping("/{id}")
    public ProviderResponseDTO updateProvider(@PathVariable int id,@RequestBody ProviderResponseDTO providerDTO){
        return providerService.updateProvider(id, providerDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteProvider(@PathVariable int id){
        providerService.deleteProvider(id);
    }
}