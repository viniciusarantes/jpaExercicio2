package br.gov.sp.fatec.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.*;

import br.gov.sp.fatec.model.Dono;
import br.gov.sp.fatec.services.DonoService;
import br.gov.sp.fatec.view.View;

@RestController
@CrossOrigin
@RequestMapping(value = "/dono")
public class DonoController {
	
	@Autowired
	private DonoService donoService;
	
	public void setDonoService(DonoService donoService) {
		this.donoService = donoService;
	}
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.All.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Dono salvar(@RequestBody Dono dono, HttpServletRequest request, HttpServletResponse response) {
		dono = donoService.salvar(dono);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() +
		request.getContextPath() + "/dono/getById?id=" + dono.getId());
		return dono;
	}
	
	@RequestMapping(value = "/get/{nome}")
	@JsonView(View.All.class)
	public ResponseEntity<Dono> pesquisar(@PathVariable("nome") String nome) {
		Dono d = donoService.buscar(nome);
		return new ResponseEntity<Dono>(d, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getById")
	@JsonView(View.All.class)
	public ResponseEntity<Dono> get(@RequestParam(value="id", defaultValue="0") Long id) {
		Dono dono = donoService.buscar(id);
		if(dono == null) {
			return new ResponseEntity<Dono>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Dono>(dono, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll")
	@JsonView(View.All.class)
	public ResponseEntity<List<Dono>> getAllDonos(){
		List<Dono> donos = donoService.getAll();
		if (donos == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Dono>>(donos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "getIds")
	@JsonView(View.All.class)
	public ResponseEntity<List<Long>> getIds(){
		List<Long> ids =  donoService.getIds();
		return new ResponseEntity<List<Long>>(ids, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}")
	@JsonView(View.All.class)
	public ResponseEntity<Object> deletar(@PathVariable("id") Long id) {
		boolean ret = donoService.deletar(id);
		if (ret) return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
