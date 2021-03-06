package br.gov.sp.fatec.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

@Entity
@Table(name = "animal")
public class Animal {

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ani_id")
	@JsonView(View.Main.class)
	private Long id;
	
	
	@Column(name = "ani_nome", unique=false, length = 50, nullable = false)
	@JsonView(View.Main.class)
    private String nome;
	
	@Column(name = "ani_especie", unique=false, length = 50, nullable = false)
	@JsonView(View.Main.class)
    private String especie;
	
	@Column(name = "ani_raca", unique=false, length = 50, nullable = true)
	@JsonView(View.Main.class)
    private String raca;

	@ManyToOne
	@JoinColumn(name="ani_dono_id")
	@JsonView(View.Main.class)
	private Dono dono;

	
	// GETTERS E SETTERS
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	//public Long getAni_dono_id() {return ani_dono_id;}
	//public void setAni_dono_id(Long ani_dono_id) {this.ani_dono_id = ani_dono_id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public String getEspecie() {return especie;}
	public void setEspecie(String especie) {this.especie = especie;}
	public String getRaca() {return raca;}
	public void setRaca(String raca) {this.raca = raca;}
	public Dono getDono() {return dono;}
	public void setDono(Dono dono) {this.dono = dono;}
}
