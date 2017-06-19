package br.gov.sp.fatec.repositories;

import br.gov.sp.fatec.model.Dono;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DonoRepository extends CrudRepository<Dono, Long>  {
	public Dono findByNome(String nome);
	public Dono findById(Long id);
	
	@Query("SELECT d FROM Dono d")
	public List<Dono> getAllDonos();
	
	@Query("SELECT d.id FROM Dono d")
	public List<Long> getIds();
}
