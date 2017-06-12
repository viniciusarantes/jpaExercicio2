package br.gov.sp.fatec.services;

import java.util.List;

import br.gov.sp.fatec.model.Autorizacao;

public interface AutorizacaoService {
	
	public Autorizacao salvar(Autorizacao autorizacao);
	
	public void excluir(Long idAutorizacao);
	
	public List<Autorizacao> todos();
	
	public List<Autorizacao> buscar(String nome);
	
	public Autorizacao buscarPorId(Long idAutorizacao);

}
