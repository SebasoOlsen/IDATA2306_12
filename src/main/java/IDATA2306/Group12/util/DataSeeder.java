//package IDATA2306.Group12.util;
//
//import IDATA2306.Group12.entity.*;
//import IDATA2306.Group12.repository.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class DataSeeder implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final HotelRepository hotelRepository;
//    private final ProviderRepository providerRepository;
//    private final ListingRepository listingRepository;
//    private final BookingRepository bookingRepository;
//
//    public DataSeeder(UserRepository userRepository,
//                      HotelRepository hotelRepository,
//                      ProviderRepository providerRepository,
//                      ListingRepository listingRepository,
//                      BookingRepository bookingRepository) {
//        this.userRepository = userRepository;
//        this.hotelRepository = hotelRepository;
//        this.providerRepository = providerRepository;
//        this.listingRepository = listingRepository;
//        this.bookingRepository = bookingRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        if (userRepository.count() == 0) {
//            seedUsers();
//        }
//
//        if (providerRepository.count() == 0 && hotelRepository.count() == 0 && listingRepository.count() == 0) {
//            seedHotelsAndProviders();
//        }
//    }
//
//    private void seedUsers() {
//        User user = new User();
//        user.setAreaCode("ABC");
//        user.setEmail("ad@a.na");
//        user.setFirstName("Seb");
//        user.setLastName("Olsen");
//        user.setId(1);
//        user.setPassword("123");
//        user.setTelephone("12312123");
//        userRepository.save(user);
//        System.out.println("✅ Seeded user");
//    }
//
//    private void seedHotelsAndProviders() {
//        // Providers
//        Provider provider1 = new Provider(0, "Booking.com");
//        Provider provider2 = new Provider(0, "Hotels.com");
//        providerRepository.saveAll(List.of(provider1, provider2));
//
//        // Hotels
//        Hotel h1 = new Hotel();
//        Hotel h2 = new Hotel();
//
//        h1.setId(1);
//        h1.setName("Sunshine Resort");
//        h1.setLocationType("Beach");
//        h1.setRoomTypes("Single,Double");
//        h1.setExtraFeatures("Pool,WiFi");
//        
//        h2.setId(2);
//        h2.setName("Mountain Inn");
//        h2.setLocationType("Mountain");
//        h2.setRoomTypes("Single,Suite");
//        h2.setExtraFeatures("Sauna,Parking");
//        
//        hotelRepository.saveAll(List.of(h1, h2));
//
//        // Listings
//        Listing l1 = new Listing();
//        l1.setHotel(h1);
//        l1.setProvider(provider1);
//        l1.setPrice(149);
//        l1.setCurrency("USD");
//        l1.setLink("https://booking.com/sunshine");
//
//        Listing l2 = new Listing();
//        l2.setHotel(h2);
//        l2.setProvider(provider2);
//        l2.setPrice(99);
//        l2.setCurrency("USD");
//        l2.setLink("https://hotels.com/mountain");
//
//        listingRepository.saveAll(List.of(l1, l2));
//
//        System.out.println("✅ Seeded providers, hotels, and listings");
//    }
//}
//