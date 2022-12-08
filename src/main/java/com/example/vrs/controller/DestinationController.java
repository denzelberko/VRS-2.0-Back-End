package com.example.vrs.controller;

import com.example.vrs.controller.dto.DestinationDto;
import com.example.vrs.controller.exceptions.DestinationNotFoundException;
import com.example.vrs.model.entity.Destination;
import com.example.vrs.model.repository.AttractionRepository;
import com.example.vrs.model.repository.DestinationRepository;
import com.example.vrs.model.repository.HotelRepository;
import com.example.vrs.model.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicComboPopup.ListDataHandler;

@CrossOrigin
@RestController
public class DestinationController {
    @Autowired
    private final DestinationRepository repository;

    public DestinationController(DestinationRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // PAGES: Destination Browsing (if implemented)
    @GetMapping("/destinations")
    List<Destination> retrieveAllDestinations() {
        return repository.findAll();
    }

    // PAGES: Recommendation System (TO BE UPDATED)
    @GetMapping("/destinations/{priceRange}/{priceRangeImp}/{tripLength}/{tripLengthImp}/{continent}/{continentImp}/{purpose}/{purposeImp}/{climate}/{climateImp}/{busyLevel}/{busyLevelImp}/{language}/{food}/{attractions}/{hotel}/{instagramability}/{childFriendly}/{safety}")
    List<Destination> searchDestinations(@PathVariable("priceRange") String priceRange,
            @PathVariable("priceRangeImp") String priceRangeImp, @PathVariable("tripLength") String tripLength,
            @PathVariable("tripLengthImp") String tripLengthImp, @PathVariable("continent") String continent,
            @PathVariable("continentImp") String continentImp,
            @PathVariable("purpose") String purpose, @PathVariable("purposeImp") String purposeImp,
            @PathVariable("climate") String climate, @PathVariable("continent") String climateImp,
            @PathVariable("busyLevel") String busyLevel, @PathVariable("busyLevelImp") String busyLevelImp,
            @PathVariable("language") String language, @PathVariable("food") String food,
            @PathVariable("attractions") String attractions, @PathVariable("hotel") String hotel,
            @PathVariable("instagramability") String instagramability,
            @PathVariable("childFriendly") String childFriendly, @PathVariable("safety") String safety) {

        // defining ArrayList for user parameters.
        ArrayList<String> userInputs = new ArrayList<String>();
        ArrayList<String> continents = new ArrayList<String>();
        ArrayList<String> purposes = new ArrayList<String>();
        ArrayList<String> climates = new ArrayList<String>();

        // #region assigning input parameters
        userInputs.add(priceRange);
        userInputs.add(tripLength);
        userInputs.add(busyLevel);
        userInputs.add(priceRangeImp);
        userInputs.add(tripLength);
        userInputs.add(tripLengthImp);
        userInputs.add(continentImp);
        userInputs.add(climateImp);
        userInputs.add(busyLevelImp);
        userInputs.add(language);
        userInputs.add(food);
        userInputs.add(attractions);
        userInputs.add(hotel);
        userInputs.add(instagramability);
        userInputs.add(childFriendly);
        userInputs.add(safety);
        continents.add(continent);
        purposes.add(purpose);
        climates.add(climate);
        // #endregion

        List<Destination> finalSortedDestinations = rankDestinations(userInputs, continents, purposes, climates,
                repository.findAll());
        return finalSortedDestinations;
    }

    // PAGES: Personalized Destination Page
    @GetMapping("/destinations/{id}")
    Destination retrieveDestination(@PathVariable("id") Long id) {
        return repository.findById(id).orElseThrow(
                () -> new DestinationNotFoundException(id));
    }

    // PAGES: Admin
    @PostMapping("/destinations")
    Destination createDestination(@RequestBody DestinationDto destinationDto) {
        Destination newDestination = new Destination();
        newDestination.setId(destinationDto.getId());
        newDestination.setName(destinationDto.getName());
        newDestination.setWeather(destinationDto.getWeather());
        newDestination.setKidFriendlyScore(destinationDto.getKidFriendlyScore());
        newDestination.setFoodQualityScore(destinationDto.getFoodQualityScore());
        newDestination.setPriceIndex(destinationDto.getPriceIndex());
        newDestination.setInstagramAbilityScore(destinationDto.getInstagramAbilityScore());
        newDestination.setNativeLanguage(destinationDto.getNativeLanguage());
        newDestination.setPurpose(destinationDto.getPurpose());
        newDestination.setHotelQualityScore(destinationDto.getHotelQualityScore());
        newDestination.setRecTripLength(destinationDto.getRecTripLength());
        newDestination.setCountry(destinationDto.getCountry());
        newDestination.setContinent(destinationDto.getContinent());
        newDestination.setCurrency(destinationDto.getCurrency());
        newDestination.setAttractionScore(destinationDto.getAttractionScore());
        newDestination.setSafetyScore(destinationDto.getSafetyScore());
        newDestination.setPopularity(destinationDto.getPopularity());
        newDestination.setImageURL(destinationDto.getImageURL());
        return repository.save(newDestination);
    }

    @PutMapping("/destinations/{id}")
    Destination updateDestination(@RequestBody DestinationDto destinationDto, @PathVariable("id") Long destinationId) {
        Destination finalDestination = repository.findById(destinationId)
                .map(destination -> {
                    // #region Set New Attributes
                    Destination currDestination = repository.findById(destinationId).orElseThrow(
                            () -> new DestinationNotFoundException(destinationId));
                    destination.setName(
                            destinationDto.getName() == null ? currDestination.getName() : destinationDto.getName());
                    destination.setWeather(destinationDto.getWeather() == null ? currDestination.getWeather()
                            : destinationDto.getWeather());
                    destination.setKidFriendlyScore(
                            destinationDto.getKidFriendlyScore() == null ? currDestination.getKidFriendlyScore()
                                    : destinationDto.getKidFriendlyScore());
                    destination.setFoodQualityScore(
                            destinationDto.getFoodQualityScore() == null ? currDestination.getFoodQualityScore()
                                    : destinationDto.getFoodQualityScore());
                    destination.setPriceIndex(destinationDto.getPriceIndex() == null ? currDestination.getPriceIndex()
                            : destinationDto.getPriceIndex());
                    destination.setInstagramAbilityScore(destinationDto.getInstagramAbilityScore() == null
                            ? currDestination.getInstagramAbilityScore()
                            : destinationDto.getInstagramAbilityScore());
                    destination.setNativeLanguage(
                            destinationDto.getNativeLanguage() == null ? currDestination.getNativeLanguage()
                                    : destinationDto.getNativeLanguage());
                    destination.setPurpose(destinationDto.getPurpose() == null ? currDestination.getPurpose()
                            : destinationDto.getPurpose());
                    destination.setHotelQualityScore(
                            destinationDto.getHotelQualityScore() == null ? currDestination.getHotelQualityScore()
                                    : destinationDto.getHotelQualityScore());
                    destination.setRecTripLength(
                            destinationDto.getRecTripLength() == null ? currDestination.getRecTripLength()
                                    : destinationDto.getRecTripLength());
                    destination.setCountry(destinationDto.getCountry() == null ? currDestination.getCountry()
                            : destinationDto.getCountry());
                    destination.setContinent(destinationDto.getContinent() == null ? currDestination.getContinent()
                            : destinationDto.getContinent());
                    destination.setCurrency(destinationDto.getCurrency() == null ? currDestination.getCurrency()
                            : destinationDto.getCurrency());
                    destination.setAttractionScore(
                            destinationDto.getAttractionScore() == null ? currDestination.getAttractionScore()
                                    : destinationDto.getAttractionScore());
                    destination
                            .setSafetyScore(destinationDto.getSafetyScore() == null ? currDestination.getSafetyScore()
                                    : destinationDto.getSafetyScore());
                    destination.setPopularity(destinationDto.getPopularity() == null ? currDestination.getPopularity()
                            : destinationDto.getPopularity());
                    destination.setImageURL(destinationDto.getImageURL() == null ? currDestination.getImageURL()
                            : destinationDto.getImageURL());
                    // #endregion
                    return repository.save(destination);
                })
                .orElseGet(() -> {
                    Destination newDestination = new Destination();
                    newDestination.setId(destinationId);
                    return repository.save(newDestination);
                });
        return finalDestination;
    }

    // PAGES: Admin
    @DeleteMapping("/destinations/{id}")
    void deleteDestination(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    // This function takes in a list of strings corresponding to each single-answer
    // question asked by the user (length 16),
    // lists of strings for each multiple-option question (variable length), and a
    // list of all the destination objects.
    // The first 3 qs are not "importance" questions, the remaining are (in order).
    // It calculates the score for each destination and stores it in the
    // destinations table, along with the destination's rank.
    public List<Destination> rankDestinations(ArrayList<String> userInputs, ArrayList<String> continents,
            ArrayList<String> purposes, ArrayList<String> climates, List<Destination> destinations) {
        double[] multiplier = new double[13]; // array of multipliers based on user input
        double[] scores = new double[destinations.size()]; // array of scores for each destination

        for (int i = 3; i < 15; i++) { // goes through each "importance" response
            Double response;
            if (userInputs.get(i).equals("not at all")) {
                response = 0.0;
            } else if (userInputs.get(i).equals("not too much")) {
                response = 1.0;
            } else if (userInputs.get(i).equals("somewhat")) {
                response = 2.0;
            } else if (userInputs.get(i).equals("very")) {
                response = 3.0;
            } else {
                response = 4.0;
            }
            multiplier[i - 3] = response / 4; // maps response to multiplier
        }
        for (int i = 0; i < destinations.size(); i++) { // for each destination
            double[] catScore = new double[13]; // array of scores for each category for this destination

            // price range (index 0):
            int priceVar; // maps user's input to value
            if (userInputs.get(0).equals("low")) {
                priceVar = 1;
            } // low price range
            else if (userInputs.get(0).equals("medium")) {
                priceVar = 5;
            } // medium
            else {
                priceVar = 9;
            } // high
            int priceDiff = (int) Math.abs(priceVar - destinations.get(i).getPriceIndex()); // absolute difference
            if (priceDiff <= 1) {
                catScore[0] = 1;
            } // map difference to score
            else if (priceDiff <= 3) {
                catScore[0] = 0.25;
            } else if (priceDiff <= 5) {
                catScore[0] = -0.5;
            } else {
                catScore[0] = -1;
            }

            // trip length (index 1):
            int userTripLength; // user's input as value (0,1,2)
            if (userInputs.get(1).equals("weekend")) {
                userTripLength = 0;
            } else if (userInputs.get(1).equals("one week")) {
                userTripLength = 1;
            } else {
                userTripLength = 2;
            }
            int destTripLength; // destination's trip length is mapped to value
            if (destinations.get(i).getRecTripLength().equals("weekend")) {
                destTripLength = 0;
            } else if (destinations.get(i).getRecTripLength().equals("one week")) {
                destTripLength = 1;
            } else {
                destTripLength = 2;
            }
            int tripLengthDiff = Math.abs(userTripLength - destTripLength);
            if (tripLengthDiff == 0) {
                catScore[1] = 1;
            } // map difference to score
            else if (tripLengthDiff == 1) {
                catScore[1] = 0;
            } else {
                catScore[1] = -1;
            }

            // continents (own list, index 2):
            if (continents.contains(destinations.get(i).getContinent())) { // dest's continent is in list of user's
                                                                           // desired continents
                catScore[2] = 1;
            } else {
                catScore[2] = -1;
            }

            // purpose (own list, index 3):
            if (purposes.contains(destinations.get(i).getPurpose())) {
                catScore[3] = 1;
            } else {
                catScore[3] = -1;
            }

            // climate (own list, index 4):
            if (climates.contains(destinations.get(i).getWeather())) {
                catScore[4] = 1;
            } else {
                catScore[4] = -1;
            }

            // busy (input index 2, output index 5):
            double busyVar;
            if (userInputs.get(2).equals("not busy")) {
                busyVar = 1.0;
            } // not busy
            else if (userInputs.get(2).equals("somewhat")) {
                busyVar = 5.0;
            } // somewhat
            else {
                busyVar = 9.0;
            } // very
            destinations.get(i).getPopularity();
            double busyDiff = Math.abs(busyVar - destinations.get(i).getPopularity());
            if (busyDiff <= 1) {
                catScore[5] = 1;
            } // map difference to score
            else if (busyDiff <= 3) {
                catScore[5] = 0.25;
            } else if (busyDiff <= 5) {
                catScore[5] = -0.5;
            } else {
                catScore[5] = -1;
            }

            // language (index 6):
            if (destinations.get(i).getNativeLanguage().equals("English")) {
                catScore[6] = 1; // native language is English
            } else {
                catScore[6] = -1;
            } // not English

            // remaining categories based solely on data (index 7-12):
            catScore[7] = (destinations.get(i).getFoodQualityScore() - 5.0) / 5.0;
            catScore[8] = (destinations.get(i).getAttractionScore() - 5.0) / 5.0;
            catScore[9] = (destinations.get(i).getHotelQualityScore() - 5.0) / 5.0;
            catScore[10] = (destinations.get(i).getInstagramAbilityScore() - 5.0) / 5.0;
            catScore[11] = (destinations.get(i).getKidFriendlyScore() - 5.0) / 5.0;
            catScore[12] = (destinations.get(i).getSafetyScore() - 5.0) / 5.0;

            // compute overall score for destination
            Double score = 0.0;
            for (int j = 0; j < 13; j++) { // compute dot product of multiplier and catScore vectors
                score += multiplier[j] * catScore[j];
            }
            score += 13;
            score = (score * 100.00) / 26.00;
            score = (double) (Math.round(score * 100.00) / 100.00);
            destinations.get(i).setScore(score); // set destination's score in database
            scores[i] = score; // add score to list
        }
        Arrays.sort(scores); // sort scores from least to greatest
        List<Destination> rankedDestinations = new ArrayList<Destination>(); // list with ranked destinations

        // assign rank to each destination and create sorted list
        for (int i = scores.length - 1; i >= 0; i--) { // go from best score to worst score
            int numTied = 0; // count how many previously ranked destinations have the same score
            while (i + numTied + 1 < scores.length && scores[i] == scores[i + numTied + 1]) { // first check index does
                                                                                              // not go OB
                numTied++; // if previous score is the same, increase variable
            }
            for (int j = 0; j < destinations.size(); j++) { // find destination with this score
                if (scores[i] == destinations.get(j).getScore()) {
                    if (numTied == 0) { // skips past tied destinations already ranked
                        destinations.get(j).setRank(scores.length - i); // set destination's rank
                        rankedDestinations.add(destinations.get(j)); // add destination to end of list
                    } else {
                        numTied--;
                    } // counts destinations skipped until 0 are left
                }
            }
        }
        return rankedDestinations.subList(0, 5); // return list of destinations ranked from highest to lowest score
    }

}
