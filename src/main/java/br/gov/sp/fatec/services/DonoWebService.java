package br.gov.sp.fatec.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.*;
import br.gov.sp.fatec.model.Dono;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/dono")
public class DonoWebService {
	
	@Autowired
	private DonoService donoService;
	public void setDonoService(DonoService donoService) {
		this.donoService = donoService;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.All.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Dono salvar(@RequestBody Dono dono, HttpServletRequest request, HttpServletResponse response) {
		dono = donoService.insertDonoWeb(dono);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() +
		request.getContextPath() + "/usuario/getById?id=" + dono.getId());
		return dono;
	}
	
	@RequestMapping(value = "/get/{nome}")
	@JsonView(View.All.class)
	public ResponseEntity<Dono> pesquisar(@PathVariable("nome") String nome) {
		return new ResponseEntity<Dono>(donoService.buscarDono(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getById")
	@JsonView(View.All.class)
	public ResponseEntity<Dono> get(@RequestParam(value="id", defaultValue="1") Long id) {
		Dono dono = donoService.buscarId(id);
		if(dono == null) {
			return new ResponseEntity<Dono>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Dono>(dono, HttpStatus.OK);
	}
}
