package br.gov.sp.fatec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Dono;
import br.gov.sp.fatec.repositories.DonoRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements UserDetailsService {
	
	@Autowired
	private DonoRepository donoRepo;

	/**
	 * @param usuarioRepo the usuarioRepo to set
	 */
	public void setUsuarioRepo(DonoRepository usuarioRepo) {
		this.donoRepo = usuarioRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Dono dono = donoRepo.findByNome(username);
		if(dono == null) {
			throw new UsernameNotFoundException(username);
		}
		return dono;
	}

}
