package org.springframework.samples.petclinic.owner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Owner class methods addPet and getPet without Spring context.
 */
public class OwnerTests {

	private Owner owner;

	@BeforeEach
	void setUp() {
		owner = new Owner();
	}

	@Test
	void addPet_shouldAddPetWithNullId() {
		Pet newPet = new Pet();
		newPet.setId(null); // means isNew() == true

		int initialSize = owner.getPets().size();
		owner.addPet(newPet);

		assertThat(owner.getPets()).contains(newPet);
		assertThat(owner.getPets().size()).isEqualTo(initialSize + 1);
	}

	@Test
	void addPet_shouldNotAddPetWithNonNullId() {
		Pet existingPet = new Pet();
		existingPet.setId(1);

		int initialSize = owner.getPets().size();
		owner.addPet(existingPet);

		// According to current behavior addPet only adds pets if pet.isNew(),
		// which means id == null, so this pet with non-null id should NOT be added
		assertThat(owner.getPets()).doesNotContain(existingPet);
		assertThat(owner.getPets().size()).isEqualTo(initialSize);
	}

	@Test
	void getPet_byName_caseInsensitive() {
		Pet pet = new Pet();
		pet.setId(1);
		pet.setName("Fido");
		owner.getPets().add(pet);

		Pet found = owner.getPet("fido");
		assertThat(found).isEqualTo(pet);
	}

	@Test
	void getPet_byName_returnsNullWhenNotFound() {
		Pet pet = new Pet();
		pet.setId(1);
		pet.setName("Fido");
		owner.getPets().add(pet);

		Pet found = owner.getPet("Max");
		assertThat(found).isNull();
	}

	@Test
	void getPet_byName_ignoreNewTrue_skipsNewPetsWithSameName() {
		Pet newPetWithName = new Pet();
		newPetWithName.setId(null); // new pet
		newPetWithName.setName("Bella");

		Pet oldPetWithSameName = new Pet();
		oldPetWithSameName.setId(2);
		oldPetWithSameName.setName("bella");

		owner.getPets().add(newPetWithName);
		owner.getPets().add(oldPetWithSameName);

		Pet found = owner.getPet("Bella", true);

		// should skip the newPetWithName and find oldPetWithSameName
		assertThat(found).isEqualTo(oldPetWithSameName);
	}

	@Test
	void getPet_byName_ignoreNewTrue_returnsNullIfAllNewPetsWithSameName() {
		Pet newPetWithName = new Pet();
		newPetWithName.setId(null); // new pet
		newPetWithName.setName("Charlie");
		owner.getPets().add(newPetWithName);

		Pet found = owner.getPet("Charlie", true);

		// should skip the newPetWithName and return null
		assertThat(found).isNull();
	}

	@Test
	void getPet_byId_matchesNonNewPets() {
		Pet pet1 = new Pet();
		pet1.setId(1);
		pet1.setName("Pet1");

		Pet pet2 = new Pet();
		pet2.setId(null); // new pet
		pet2.setName("Pet2");

		owner.getPets().add(pet1);
		owner.getPets().add(pet2);

		Pet found = owner.getPet(1);
		assertThat(found).isEqualTo(pet1);
	}

	@Test
	void getPet_byId_returnsNullWhenNoMatch() {
		Pet pet1 = new Pet();
		pet1.setId(1);
		owner.getPets().add(pet1);

		Pet found = owner.getPet(99);
		assertThat(found).isNull();
	}

}
