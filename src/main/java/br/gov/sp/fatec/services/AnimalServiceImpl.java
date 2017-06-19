package br.gov.sp.fatec.services;
import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.repositories.AnimalRepository;
import br.gov.sp.fatec.repositories.DonoRepository;
import br.gov.sp.fatec.web.controller.AnimalService;
import br.gov.sp.fatec.model.Animal;
import br.gov.sp.fatec.model.Dono;

@Service("animalService")
public class AnimalServiceImpl implements AnimalService {
	public AnimalServiceImpl(){
	}
	

	@Autowired
	private AnimalRepository animalRepo;
	@Autowired
	private DonoRepository donoRepo;
	
	@Transactional
	public void insertAnimal(String nome, String especie, String raca, Dono dono) {
		Animal animal = new Animal();
		animal.setNome(nome);
		animal.setEspecie(especie);
		animal.setRaca(raca);
		animal.setDono(dono);
		animalRepo.save(animal);
	}
	
	@Transactional
	public void deleteAnimal(Animal animal){
		animalRepo.delete(animal);
	}
	
	@Transactional
	public void deleteAllAnimal(){
		animalRepo.deleteAll();
	}
	
	public void setAnimalRepo(AnimalRepository animalRepo) {
		this.animalRepo = animalRepo;
	}

	@Override
	public Animal salvar(Animal animal) {
		Dono dono = donoRepo.findById(animal.getDono().getId());
		animal.setDono(dono);
		animalRepo.save(animal);
		return animal;
	}

	@Override
	public Animal buscar(Long id) {
		if (animalRepo == null) return null;
		return animalRepo.findById(id);
	}
}	
