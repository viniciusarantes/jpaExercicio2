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
@RequestMapping(value = "/animal")
public class AnimalController {

	@Autowired
	private DonoService donoService;
	
	public void setDonoService(DonoService donoService) {
		this.donoService = donoService;
	}
	
	@RequestMapping(value="/animal")
	public String fim() {
		return "Deu animal";
	}
}
