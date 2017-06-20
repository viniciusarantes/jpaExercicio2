package br.gov.sp.fatec.services;

import java.util.List;

import br.gov.sp.fatec.model.*;

public interface DonoService {
	
	public Dono salvar(Dono dono);
	public Dono buscar(String nome);
	public Dono buscar(Long id);
	public List<Long> getIds();
	public boolean deletar(Long id);
	public List<Dono> getAll();
}
