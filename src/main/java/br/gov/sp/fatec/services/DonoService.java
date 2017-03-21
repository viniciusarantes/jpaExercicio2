package br.gov.sp.fatec.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.repositories.DonoRepository;
import br.gov.sp.fatec.model.Dono;

@Service("donoService")
public class DonoService {
	
	public DonoService(DonoRepository donoRepo){
		this.donoRepo = donoRepo;
	}
	
	@Autowired
	private DonoRepository donoRepo;
	
	@Transactional
	public void insertDono(String nome, String endereco, String tel) {
		Dono dono1 = new Dono();
		dono1.setNome(nome);
		dono1.setEndereco(endereco);
		dono1.setTelefone(tel);
		donoRepo.save(dono1);
	}
	
	@Transactional
	public void deleteDono(Dono dono){
		donoRepo.delete(dono);
	}
	
	
}
