package IDATA2306.Group12.controller;

import IDATA2306.Group12.dto.ProviderDTO;
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
    public List<ProviderDTO> getAllproviders(){
        try{
            return providerService.getAllProviders();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    @GetMapping("/{id}")
    public ProviderDTO getProviderById(@PathVariable int id){
        return providerService.getProviderById(id);
    }
    @PostMapping
    public ProviderDTO createProvider(@RequestBody ProviderDTO providerDTO){
        return providerService.createProvider(providerDTO);
    }
    @PutMapping("/{id}")
    public ProviderDTO updateProvider(@PathVariable int id,@RequestBody ProviderDTO providerDTO){
        return providerService.updateProvider(id, providerDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteProvider(@PathVariable int id){
        providerService.deleteProvider(id);
    }
}