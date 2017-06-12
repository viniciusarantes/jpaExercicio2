package br.gov.sp.fatec.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Autorizacao;

public interface AutorizacaoRepository extends CrudRepository<Autorizacao, Long> {

	public List<Autorizacao> findByNomeContainsIgnoreCase(String nome);
	
}
