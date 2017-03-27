package br.gov.sp.fatec.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.repositories.DonoRepository;
import br.gov.sp.fatec.model.Dono;

@Service("donoService")
public class DonoService {
	
	public DonoService(){
	}
	
	@Autowired
	private DonoRepository donoRepo;
	

	@Transactional
	public void insertDono(String nome, String endereco, String tel) throws Exception {
		Dono dono = donoRepo.findByNome(nome);
		if (dono!=null && nome.equals(dono.getNome())) throw new Exception("Dono já cadastrado no sistema!");
		dono = new Dono();
		dono.setNome(nome);
		dono.setEndereco(endereco);
		dono.setTelefone(tel);
		donoRepo.save(dono);
	}
	
	@Transactional
	public void deleteDono(Dono dono){
		donoRepo.delete(dono);
	}

	@Transactional
	public void deleteAllDono(){
		donoRepo.deleteAll();
	}
	
	public void setDonoRepo(DonoRepository donoRepo) {
		this.donoRepo = donoRepo;
	}
}
