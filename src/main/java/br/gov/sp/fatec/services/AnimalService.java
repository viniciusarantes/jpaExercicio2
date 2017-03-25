package br.gov.sp.fatec.services;
import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.repositories.AnimalRepository;
import br.gov.sp.fatec.model.Animal;

@Service("animalService")
public class AnimalService {
	public AnimalService(AnimalRepository animalRepo){
		this.animalRepo = animalRepo;
	}
	
	@Autowired
	private AnimalRepository animalRepo;
	
	@Transactional
	public void insertAnimal(String nome, String especie, String raca) {
//	public void insertAnimal(String nome, String especie, String raca, Long ani_dono_id) {
		
		Animal animal1 = new Animal();
		animal1.setNome(nome);
		// como inserir o id do dono?
		// animal1.setAni_dono_id(ani_dono_id);
		animal1.setEspecie(especie);
		animal1.setRaca(raca);
		animalRepo.save(animal1);
	}
	
	@Transactional
	public void deleteAnimal(Animal animal){
		animalRepo.delete(animal);
	}
	
}	
