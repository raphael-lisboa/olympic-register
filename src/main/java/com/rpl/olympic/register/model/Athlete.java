package com.rpl.olympic.register.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "ATHLETE")
public class Athlete implements Serializable {
	private static final long	serialVersionUID	= -8616528352340479643L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long				id;
	private String				name;
	@Enumerated(EnumType.STRING)
	private Contry				contry;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "ATHLETE_DISCIPLINE",
				joinColumns = @JoinColumn(name = "ATHLETE_ID", referencedColumnName = "id") ,
				inverseJoinColumns = @JoinColumn(name = "DISCIPLINE_ID", referencedColumnName = "id") )
	private List<Discipline>	disciplines;

	public Athlete() {
	}

	public Athlete(Long id, String name, Contry contry, List<Discipline> disciplines) {
		super();
		this.id = id;
		this.name = name;
		this.contry = contry;
		this.disciplines = disciplines;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contry getContry() {
		return contry;
	}

	public void setContry(Contry contry) {
		this.contry = contry;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	@Override
	public String toString() {
		return String.format("Athlete [id=%s, name=%s, contry=%s]", id, name, contry);
	}

}
