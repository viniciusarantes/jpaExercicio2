package br.gov.sp.fatec.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.repositories.DonoRepository;
import br.gov.sp.fatec.model.Dono;

@Service("donoService")
public class DonoServiceImpl implements DonoService{
	
	public DonoServiceImpl(){
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

	public Dono salvar(Dono dono) {
		String crypto = encodeMd5(dono.getSenha());
		dono.setSenha(crypto);
		donoRepo.save(dono);
		return dono;
	}

	public Dono buscar(String nome) {
		if (donoRepo == null) return null;
		return donoRepo.findByNome(nome);
	}

	public Dono buscar(Long id) {
		if (donoRepo == null) return null;
		return donoRepo.findById(id);
	}
	
	public List<Long> getIds(){
		return donoRepo.getIds();
	}
	
	@Override
	public boolean deletar(Long id){
		try {
			donoRepo.delete(id);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Dono> getAll(){
		return (List<Dono>) donoRepo.findAll();
	}
	
	
	public String encodeMd5(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }
	
}
