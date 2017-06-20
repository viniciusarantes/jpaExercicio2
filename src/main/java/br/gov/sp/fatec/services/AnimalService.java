package br.gov.sp.fatec.services;

import java.util.List;

import br.gov.sp.fatec.model.Animal;

public interface AnimalService {
	
	public Animal salvar(Animal animal);
	public Animal buscar(Long id);
	public boolean deletar(Long id);
	public List<Animal> getAll();
}