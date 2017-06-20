package br.gov.sp.fatec.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.*;

import br.gov.sp.fatec.model.Animal;
import br.gov.sp.fatec.services.AnimalService;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/animal")
public class AnimalController {

	@Autowired
	private AnimalService animalService;
	
	public void setAnimalService(AnimalService animalService) {
		this.animalService = animalService;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.All.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Animal salvar(@RequestBody Animal animal, HttpServletRequest request, HttpServletResponse response) {
		animal = animalService.salvar(animal);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() +
		request.getContextPath() + "/animal/getById?id=" + animal.getId());
		return animal;
	}
	
	@RequestMapping(value = "/getById")
	@JsonView(View.All.class)
	public ResponseEntity<Animal> get(@RequestParam(value="id", defaultValue="1") Long id) {
		Animal animal = animalService.buscar(id);
		if(animal == null) {
			return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Animal>(animal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll")
	@JsonView(View.All.class)
	public ResponseEntity<List<Animal>> getAllAnimais(){
		List<Animal> animais = animalService.getAll();
		if (animais == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Animal>>(animais, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}")
	@JsonView(View.All.class)
	public ResponseEntity<List<Animal>> delete(@PathVariable("id") Long id) {
		boolean ret = animalService.deletar(id);
		if (ret) {
			List<Animal> animais = animalService.getAll();
			return new ResponseEntity<List<Animal>>(animais, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
