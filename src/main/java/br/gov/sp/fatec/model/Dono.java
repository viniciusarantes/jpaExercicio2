package br.gov.sp.fatec.model;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

@Entity
@Table(name = "dono")

public class Dono implements UserDetails{
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "dono_id")
	@JsonView(View.Main.class)
	private Long id;
	
	@Column(name = "dono_nome", unique=true, length = 50, nullable = false)
	@JsonView(View.Main.class)
    private String nome;
	
	@Column(name = "dono_senha", unique=true, length = 50, nullable = false)
	private String senha;
	
	@Column(name = "dono_endereco", unique=false, length = 100, nullable = true)
	@JsonView(View.Main.class)
    private String endereco;
	
	@Column(name = "dono_telefone", unique=false, length = 20, nullable = true)
	@JsonView(View.Main.class)
    private String telefone;

	@OneToMany(mappedBy = "dono", targetEntity = Animal.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private Collection<Animal> animais;
	private List<Animal> animais;
	
	@ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "dono_autorizacao", 
//    	joinColumns = { @JoinColumn(name = "dono_id") }, 
//    	inverseJoinColumns = { @JoinColumn(name = "aut_id") })
	@JoinTable(name = "dono_autorizacao", 
	joinColumns = { @JoinColumn(name = "da_dono_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "da_aut_id") })
    private List<Autorizacao> autorizacoes;
	
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
	public String getSenha() {return senha;}
	public void setSenha(String senha) {this.senha = senha;}
	
	
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.autorizacoes;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return senha;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return nome;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
}
