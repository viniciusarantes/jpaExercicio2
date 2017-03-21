package br.gov.sp.fatec.repositories;

import org.springframework.data.repository.CrudRepository;
import br.gov.sp.fatec.model.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Long> {

}
