package br.gov.sp.fatec.web.controller;

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
//@RequestMapping(value = "/dono")
public class DonoController {
	
	@Autowired
	private DonoService donoService;
	
	public void setDonoService(DonoService donoService) {
		this.donoService = donoService;
	}
	
	@RequestMapping(value="/")
	public String inicio() {
		return "Deu certo";
	}
	
	@RequestMapping(value="/dono")
	public String meio() {
		return "Deu dono";
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.All.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Dono salvar(@RequestBody Dono dono, HttpServletRequest request, HttpServletResponse response) {
		dono = donoService.salvar(dono);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() +
		request.getContextPath() + "/usuario/getById?id=" + dono.getId());
		return dono;
	}
	
	@RequestMapping(value = "/get/{nome}")
	@JsonView(View.All.class)
	public ResponseEntity<Dono> pesquisar(@PathVariable("nome") String nome) {
		Dono d = donoService.buscar(nome);
		System.out.println("\n******************\n");
		System.out.println("Id:"+Long.toString(d.getId())+"\nNome: "+d.getNome()+"\nTelefone: "+d.getTelefone());
		System.out.println("\n******************\n");
		return new ResponseEntity<Dono>(d, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getById")
	@JsonView(View.All.class)
	public ResponseEntity<Dono> get(@RequestParam(value="id", defaultValue="1") Long id) {
		Dono dono = donoService.buscar(id);
		if(dono == null) {
			return new ResponseEntity<Dono>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Dono>(dono, HttpStatus.OK);
	}
}
