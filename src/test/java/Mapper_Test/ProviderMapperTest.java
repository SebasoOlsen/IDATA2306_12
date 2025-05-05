// package IDATA2306.Group12.mapper;
//
// import IDATA2306.Group12.dto.provider.ProviderResponseDTO;
// import IDATA2306.Group12.entity.Provider;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
//
// class ProviderMapperTest {
//
// private final ProviderMapper mapper = new ProviderMapper();
//
// @Test
// void toResponseDTO_shouldMapCorrectly() {
// Provider provider = new Provider(1, "TestProvider");
// ProviderResponseDTO dto = mapper.toResponseDTO(provider);
// assertEquals("TestProvider", dto.getName());
// }
//
// @Test
// void toEntity_shouldMapCorrectly() {
// ProviderResponseDTO dto = new ProviderResponseDTO();
// dto.setId(2);
// dto.setName("MappedProvider");
//
// Provider provider = mapper.toEntity(dto);
// assertEquals("MappedProvider", provider.getName());
// }
// }
