package IDATA2306.Group12.controller;

import IDATA2306.Group12.entity.Provider;
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
    public List<Provider> getAllproviders(){
        try{
            return providerService.getAllProviders();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    @GetMapping("/{id}")
    public Provider getProviderById(@PathVariable int id){
        return providerService.getProviderById(id);
    }
    @PostMapping
    public Provider createProvider(@RequestBody Provider provider){
        return providerService.createProvider(provider);
    }
    @PutMapping("/{id}")
    public Provider updateProvider(@PathVariable int id,@RequestBody Provider provider){
        return providerService.updateProvider(id, provider);
    }
    @DeleteMapping("/{id}")
    public void deleteProvider(@PathVariable int id){
        providerService.deleteProvider(id);
    }
}