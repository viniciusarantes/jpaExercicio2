package br.gov.sp.fatec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.gov.sp.fatec.model.*;
import br.gov.sp.fatec.repositories.*;
import br.gov.sp.fatec.services.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AnimalRepository animalRepo = (AnimalRepository) context.getBean("animalRepository");
		DonoRepository donoRepo = (DonoRepository) context.getBean("donoRepository");
		
		
		// ambos os services pedem para passar um repository como parametro
		// porém ainda não sei como fazer
		AnimalService animalServ = (AnimalService)context.getBean("animalService");
		DonoService donoServ = (DonoService)context.getBean("donoService");
		animalServ.setAnimalRepo(animalRepo);
		donoServ.setDonoRepo(donoRepo);
		
		try {
			donoServ.insertDono("Victor", "Rua dos bobos", "0000-0000");
			animalServ.insertAnimal("Toto", "Cachorrineo", "Pit-Bitoca", donoRepo.findByNome("Victor"));
			System.out.println("Registros inseridos com sucesso!");
		}
		catch(Exception e) {
			System.out.println("Erro esperado! Rollback realizado!");
			e.printStackTrace();
		}
		
    }
}
