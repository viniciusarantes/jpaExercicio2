package br.gov.sp.fatec;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.gov.sp.fatec.model.*;
import br.gov.sp.fatec.repositories.*;
import br.gov.sp.fatec.services.*;

/**
 * @author Victor de Campos
 * @author Vinícius Arantes
 * 
 * Exercicio 2 - Spring Data JPA
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
		Dono donoInsert = new Dono();
		
		try {
			donoServ.deleteAllDono();
			animalServ.deleteAllAnimal();
			
			donoServ.insertDono("Victor", "Rua dos bobos", "0000-0000");
			donoInsert = donoRepo.findByNome("Victor");
			animalServ.insertAnimal("Toto", "Cachorro", "Pit-Bitoca", donoInsert);
			animalServ.insertAnimal("Miau", "Gato", "Tomba Lata", donoInsert);
			
			donoServ.insertDono("Vinicius", "Rua de Trás", "9999-9999");
			donoInsert = donoRepo.findByNome("Vinicius");
			animalServ.insertAnimal("Suzana", "Cachorro", "Salsicha", donoInsert);
			animalServ.insertAnimal("Hantaro", "Hamster", "", donoInsert);
			
			donoServ.insertDono("Arnold", "Rua da frente", "1234-5678");
			donoInsert = donoRepo.findByNome("Arnold");
			animalServ.insertAnimal("Schwaszenegger", "Cachorro", "", donoInsert);
			System.out.println("\n*** Registros inseridos com sucesso! ***\n");
			
			String msg = "";
			List<Dono> buscaDono = null;
			List<Animal> buscaAnimal = null;
			
			msg += "\nCachorros registrados no sistema:";
			buscaAnimal = animalRepo.buscaCachorros();
			for (Animal animal : buscaAnimal) {
				msg += "\n\t"+animal.getNome();				
			}
			
			msg += "\n\nLista de donos e seus animais";
			buscaDono = donoRepo.getAllDonos();
			for (Dono dono : buscaDono){
				msg += "\nEste é " + dono.getNome() + " (ID: "+dono.getId()+")";
				buscaAnimal = animalRepo.findByDono(dono);
				for(Animal animal : buscaAnimal) {
					msg += "\n\t"+dono.getNome()+" tem um " + animal.getEspecie() + " chamado " + animal.getNome();
				}
			}
			
			System.out.print(msg);
			
			
			
		}
		catch(Exception e) {
			System.out.println("Erro esperado! Rollback realizado!");
			e.printStackTrace();
		}
		
    }
}
