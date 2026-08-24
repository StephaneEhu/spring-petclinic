package org.springframework.samples.petclinic.owner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class VisitTests {

	@Test
	public void visitDefaultDateIsTomorrow() {
		Visit visit = new Visit();
		assertThat(visit.getDate()).isEqualTo(LocalDate.now().plusDays(1));
	}

	@Test
	public void setDateAndGetDateRoundTrip() {
		Visit visit = new Visit();
		LocalDate date = LocalDate.of(2025, 6, 15);
		visit.setDate(date);
		assertThat(visit.getDate()).isEqualTo(date);
	}

	@Test
	public void setDescriptionAndGetDescriptionRoundTrip() {
		Visit visit = new Visit();
		String desc = "Routine checkup";
		visit.setDescription(desc);
		assertThat(visit.getDescription()).isEqualTo(desc);
	}

	@Test
	public void isNewIsTrueWhenIdIsNull() {
		Visit visit = new Visit();
		// id is from BaseEntity and defaults to null
		assertThat(visit.isNew()).isTrue();
	}

	@Test
	public void isNewIsFalseAfterSetId() {
		Visit visit = new Visit();
		visit.setId(1);
		assertThat(visit.isNew()).isFalse();
	}

}
