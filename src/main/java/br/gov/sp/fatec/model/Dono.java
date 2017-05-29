package br.gov.sp.fatec.model;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

@Entity
@Table(name = "dono")
public class Dono {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "dono_id")
	@JsonView(View.Main.class)
	private Long id;
	
	@Column(name = "dono_nome", unique=true, length = 50, nullable = false)
    private String nome;
	
	@Column(name = "dono_endereco", unique=false, length = 100, nullable = false)
    private String endereco;
	
	@Column(name = "dono_telefone", unique=false, length = 20, nullable = false)
    private String telefone;

	@OneToMany(mappedBy = "dono", targetEntity = Animal.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Animal> animais;
    
	
	// GETTERS E SETTERS
	public Collection<Animal> getAnimais() {return animais;}
	public String getEndereco() {return endereco;}
	public void setEndereco(String endereco) {this.endereco = endereco;}
	public String getTelefone() {return telefone;}
	public void setTelefone(String telefone) {this.telefone = telefone;}
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
}
