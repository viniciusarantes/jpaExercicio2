package br.gov.sp.fatec.repositories;

import br.gov.sp.fatec.model.Dono;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DonoRepository extends CrudRepository<Dono, Long>  {
	public Dono findByNome(String nome);
}
