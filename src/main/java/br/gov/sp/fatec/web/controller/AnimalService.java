package br.gov.sp.fatec.web.controller;

import br.gov.sp.fatec.model.Animal;

public interface AnimalService {
	
	public Animal salvar(Animal animal);
	public Animal buscar(Long id);
}