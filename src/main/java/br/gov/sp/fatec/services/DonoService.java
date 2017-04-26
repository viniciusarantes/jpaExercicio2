package br.gov.sp.fatec.services;

import br.gov.sp.fatec.model.*;

public interface DonoService {
	
	public Dono salvar(Dono dono);
	public Dono buscar(String nome);
	public Dono buscar(Long id);
}
