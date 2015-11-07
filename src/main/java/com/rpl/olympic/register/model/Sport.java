package com.rpl.olympic.register.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "SPORT")
public class Sport implements Serializable {

	private static final long	serialVersionUID	= 6730735308104964349L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long				id;
	private String				name;
	private String				descption;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "sport")
	private List<Discipline>	disciplines;

	public Sport() {
	}

	public Sport(Long id, String name, String descption, List<Discipline> disciplines) {
		super();
		this.id = id;
		this.setName(name);
		this.descption = descption;
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

	public String getDescption() {
		return descption;
	}

	public void setDescption(String descption) {
		this.descption = descption;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	@Override
	public String toString() {
		return String.format("Sport [id=%s, name=%s, descption=%s, disciplines=%s]", id, name, descption);
	}

}
