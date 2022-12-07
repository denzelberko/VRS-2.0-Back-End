package com.example.vrs;

import com.example.vrs.model.entity.Destination;
import com.example.vrs.model.repository.DestinationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class vrsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private DestinationRepository destinationRepository;

	@Test
	void getDestination() throws Exception{
		MockHttpServletResponse response = mockMvc.perform(get("/destinations/1"))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());

		ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
		assertEquals(1L, receivedJson.get("id").longValue());
		assertEquals("New York City", receivedJson.get("name").textValue());
	}

    @Test
	void deleteDestination() throws Exception{
		Destination d = new Destination();
		d.setId(34L);
        d.setName("Destination Name");
        d.setWeather("Tropical");
        d.setKidFriendlyScore(10L);
        d.setFoodQualityScore(10L);
        d.setPriceIndex(10L);
        d.setInstagramAbilityScore(10L);
        d.setNativeLanguage("English");
        d.setPurpose("Sightseeing");
        d.setHotelQualityScore(10L);
        d.setRecTripLength("weekend");
        d.setCountry("Canada");
        d.setContinent("North America");
        d.setCurrency("CAD");
        d.setAttractionScore(10L);
        d.setSafetyScore(10L);
        d.setPopularity(10L);
		destinationRepository.save(d);

		MockHttpServletResponse response = mockMvc.perform(
				delete("/destinations/34").
						contentType("application/json"))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());
		assertTrue(destinationRepository.findById(34L).isEmpty());
	}

    @Test
	void addDestination() throws Exception{

		ObjectNode destinationJson = objectMapper.createObjectNode();
		destinationJson.put("id", 36L);
        destinationJson.put("name", "Destination Name");
		destinationJson.put("weather", "Tropical");
		destinationJson.put("kidFriendlyScore", 10L);
		destinationJson.put("foodQualityScore", 10L);
		destinationJson.put("priceIndex", 10L);
		destinationJson.put("instagramAbilityScore", 10L);
		destinationJson.put("nativeLanguage", "English");
		destinationJson.put("purpose", "Sightseeing");
		destinationJson.put("hotelQualityScore", 10L);
		destinationJson.put("country", "Canada");
		destinationJson.put("continent", "North America");
		destinationJson.put("popularity", 10L);
		destinationJson.put("recTripLength", "weekend");
		destinationJson.put("currency", "CAD");
		destinationJson.put("attractionScore", 10L);
		destinationJson.put("safetyScore", 10L);

		MockHttpServletResponse response = mockMvc.perform(
				post("/destinations").
						contentType("application/json").
						content(destinationJson.toString()))
				.andReturn().getResponse();

		// assert HTTP code of response is 200 OK
		assertEquals(200, response.getStatus());

		// Assert destination with id 36 exists in our repository and then get the destination object
		assertTrue(destinationRepository.findById(36L).isPresent());
		Destination addedDest = destinationRepository.findById(36L).get();

		// Assert the details of the destinations are correct
		assertEquals(36L, addedDest.getId());
        assertEquals("Destination Name", addedDest.getName());
		assertEquals("Tropical", addedDest.getWeather());
		assertEquals(10L, addedDest.getKidFriendlyScore());
		assertEquals(10L, addedDest.getFoodQualityScore());
		assertEquals(10L, addedDest.getPriceIndex());
		assertEquals(10L, addedDest.getInstagramAbilityScore());
		assertEquals("English", addedDest.getNativeLanguage());
		assertEquals("Sightseeing", addedDest.getPurpose());
		assertEquals(10L, addedDest.getHotelQualityScore());
		assertEquals("Canada", addedDest.getCountry());
		assertEquals("North America", addedDest.getContinent());
		assertEquals(10L, addedDest.getPopularity());
		assertEquals("weekend", addedDest.getRecTripLength());
		assertEquals("CAD", addedDest.getCurrency());
		assertEquals(10L, addedDest.getAttractionScore());
		assertEquals(10L, addedDest.getSafetyScore());


	}

    @Test
	void editDestination() throws Exception{

		ObjectNode destinationJson = objectMapper.createObjectNode();
		destinationJson.put("id", 36L);
        destinationJson.put("name", "Destination Name");
		destinationJson.put("weather", "Tropical");
		destinationJson.put("kidFriendlyScore", 10L);
		destinationJson.put("foodQualityScore", 10L);
		destinationJson.put("priceIndex", 10L);
		destinationJson.put("instagramAbilityScore", 10L);
		destinationJson.put("nativeLanguage", "English");
		destinationJson.put("purpose", "Sightseeing");
		destinationJson.put("hotelQualityScore", 10L);
		destinationJson.put("country", "Canada");
		destinationJson.put("continent", "North America");
		destinationJson.put("popularity", 10L);
		destinationJson.put("recTripLength", "weekend");
		destinationJson.put("currency", "CAD");
		destinationJson.put("attractionScore", 10L);
		destinationJson.put("safetyScore", 10L);

		MockHttpServletResponse response = mockMvc.perform(
				post("/destinations").
						contentType("application/json").
						content(destinationJson.toString()))
				.andReturn().getResponse();

		// assert HTTP code of response is 200 OK
		assertEquals(200, response.getStatus());

		ObjectNode destinationJson2 = objectMapper.createObjectNode();
		destinationJson2.put("id", 36L);
        destinationJson2.put("name", "Destination Name");
		destinationJson2.put("weather", "Tropical");
		destinationJson2.put("kidFriendlyScore", 10L);
		destinationJson2.put("foodQualityScore", 5L);
		destinationJson2.put("priceIndex", 10L);
		destinationJson2.put("instagramAbilityScore", 5L);
		destinationJson2.put("nativeLanguage", "English");
		destinationJson2.put("purpose", "Sightseeing");
		destinationJson2.put("hotelQualityScore", 10L);
		destinationJson2.put("country", "Canada");
		destinationJson2.put("continent", "North America");
		destinationJson2.put("popularity", 5L);
		destinationJson2.put("recTripLength", "weekend");
		destinationJson2.put("currency", "CAD");
		destinationJson2.put("attractionScore", 5L);
		destinationJson2.put("safetyScore", 5L);

		MockHttpServletResponse response2 = mockMvc.perform(
				put("/destinations/36").
						contentType("application/json").
						content(destinationJson2.toString()))
				.andReturn().getResponse();

		// assert HTTP code of response is 200 OK
		assertEquals(200, response2.getStatus());

			// Assert destination with id 36 exists in our repository and then get the destination object
			assertTrue(destinationRepository.findById(36L).isPresent());
			Destination addedDest = destinationRepository.findById(36L).get();
	
			// Assert the details of the destination are correct
			assertEquals(36L, addedDest.getId());
			assertEquals("Destination Name", addedDest.getName());
			assertEquals("Tropical", addedDest.getWeather());
			assertEquals(10L, addedDest.getKidFriendlyScore());
			assertEquals(5L, addedDest.getFoodQualityScore());
			assertEquals(10L, addedDest.getPriceIndex());
			assertEquals(5L, addedDest.getInstagramAbilityScore());
			assertEquals("English", addedDest.getNativeLanguage());
			assertEquals("Sightseeing", addedDest.getPurpose());
			assertEquals(10L, addedDest.getHotelQualityScore());
			assertEquals("Canada", addedDest.getCountry());
			assertEquals("North America", addedDest.getContinent());
			assertEquals(5L, addedDest.getPopularity());
			assertEquals("weekend", addedDest.getRecTripLength());
			assertEquals("CAD", addedDest.getCurrency());
			assertEquals(5L, addedDest.getAttractionScore());
			assertEquals(5L, addedDest.getSafetyScore());
    }

}