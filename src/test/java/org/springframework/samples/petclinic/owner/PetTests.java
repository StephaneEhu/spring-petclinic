package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PetTests {

	@Test
	public void testNewPetVisitsNotNull() {
		Pet pet = new Pet();
		assertNotNull(pet.getVisits(), "Visits collection should not be null for new Pet");
		assertEquals(0, pet.getVisits().size(), "New Pet visits size should be 0");
	}

	@Test
	public void testAddVisitIncreasesVisitsSize() {
		Pet pet = new Pet();
		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		pet.addVisit(visit);
		assertEquals(1, pet.getVisits().size(), "Visits size should be 1 after adding a visit");
	}

	@Test
	public void testAddMultipleVisitsAccumulate() {
		Pet pet = new Pet();
		Visit visit1 = new Visit();
		visit1.setDate(LocalDate.now());
		Visit visit2 = new Visit();
		visit2.setDate(LocalDate.now().plusDays(1));
		pet.addVisit(visit1);
		pet.addVisit(visit2);
		assertEquals(2, pet.getVisits().size(), "Visits size should be 2 after adding two visits");
	}

}