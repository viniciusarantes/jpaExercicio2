package br.gov.sp.fatec;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.*;
import br.gov.sp.fatec.repositories.*;
import br.gov.sp.fatec.services.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/AnimalRepositoryTest.xml" })
@Rollback
@Transactional
public class AnimalRepositoryTest {
	private static final String NOME = "Bolota";
	private static final String ESPECIE = "Cachorro";
	private static final String RACA = "Jiu-Jitsu";	
	
	@Autowired
	private DonoService donoServ;	
	public void setDonoServ(DonoService donoServ) { this.donoServ = donoServ; }
	@Autowired
	private DonoRepository donoRepo;
	public void setDonoRepo(DonoRepository donoRepo) { this.donoRepo = donoRepo; }
	
	@Autowired
	private AnimalService animalServ;	
	public void setAnimalServ(AnimalService animalServ) { this.animalServ = animalServ; }
	@Autowired
	private AnimalRepository animalRepo;
	public void setAnimalRepo(AnimalRepository animalRepo) { this.animalRepo = animalRepo; }

	@Test
	public void buscaAnimalByDonoOk() throws Exception {;
		donoServ.insertDono("Tadeu", "Rua 15", "3652-1515");
		Dono dono = donoRepo.findByNome("Tadeu");
		
		animalServ.insertAnimal(NOME, ESPECIE, RACA, dono);
		List<Animal> buscaAnimal = animalRepo.findByDono(dono);
		
		Dono animalDono = buscaAnimal.get(0).getDono();
		
		assertTrue(animalDono == dono);
	}
}



