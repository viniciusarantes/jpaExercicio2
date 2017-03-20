package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal {

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ani_id")
	private Long id;
	
	@Column(name = "ani_nome", unique=false, length = 50, nullable = false)
    private String nome;
	
	@Column(name = "ani_especie", unique=false, length = 50, nullable = false)
    private String especie;
	
	@Column(name = "ani_raca", unique=false, length = 50, nullable = true)
    private String raca;


	// GETTERS E SETTERS
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public String getEspecie() {return especie;}
	public void setEspecie(String especie) {this.especie = especie;}
	public String getRaca() {return raca;}
	public void setRaca(String raca) {this.raca = raca;}
}
