package org.springframework.samples.petclinic.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.Visit;

class BaseEntityTests {

	@Test
	void isNewShouldReturnTrueWhenIdIsNull() {
		Visit visit = new Visit();
		visit.setId(null); // explicitly set null to illustrate
		assertTrue(visit.isNew(), "isNew should be true when id is null");
	}

	@Test
	void isNewShouldReturnFalseWhenIdIsNotNull() {
		Visit visit = new Visit();
		visit.setId(123);
		assertFalse(visit.isNew(), "isNew should be false when id is not null");
	}

}
