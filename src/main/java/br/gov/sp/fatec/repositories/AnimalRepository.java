package br.gov.sp.fatec.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import br.gov.sp.fatec.model.Animal;
import br.gov.sp.fatec.model.Dono;

public interface AnimalRepository extends CrudRepository<Animal, Long> {

	@Query("SELECT a FROM Animal a WHERE a.especie LIKE '%%Cachorro%%'")
	public List<Animal> buscaCachorros();
	
	public List<Animal> findByDono(Dono dono);
	
	public Animal findById(Long id);
}
