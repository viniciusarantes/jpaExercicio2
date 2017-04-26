package br.gov.sp.fatec;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Dono;
import br.gov.sp.fatec.services.DonoServiceImpl;
import br.gov.sp.fatec.repositories.DonoRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/DonoServiceTest.xml" })
@Rollback
@Transactional
public class DonoServiceTest {

	private static final String NOME = "Tadeu";
	private static final String ENDERECO = "Rua 15";
	private static final String TELEFONE = "3652-1515";
	
	@Autowired
	private DonoServiceImpl donoServ;	
	public void setDonoServ(DonoServiceImpl donoServ) { this.donoServ = donoServ; }
	
	@Autowired
	private DonoRepository donoRepo;
	public void setDonoRepo(DonoRepository donoRepo) { this.donoRepo = donoRepo; }

	
	@Test
	public void testeInsercaoOk() throws Exception {;
		donoServ.insertDono(NOME, ENDERECO, TELEFONE);
		Dono dono = donoRepo.findByNome(NOME);
		assertTrue(dono.getId() != null);
	}
	
	@Test
	public void testeBuscaTodosOk() throws Exception {;
		donoRepo.deleteAll();
		donoServ.insertDono("Mano", "Rua 16", "3652-1616");
		donoServ.insertDono("Brown", "Rua 17", "3652-1717");
		donoServ.insertDono("Fabio", "Rua 18", "3652-1818");
		
		List<Dono> buscaDono = donoRepo.getAllDonos();
		
		assertTrue(buscaDono.size() == 3);
	}
	
}
