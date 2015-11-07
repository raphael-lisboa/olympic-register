package com.rpl.olympic.register.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISCIPLINE")
public class Discipline implements Serializable {

	private static final long	serialVersionUID	= -415824580784539991L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long				id;
	private String				name;
	private String				descption;
	private Sport				sport;
	private Genre				genre;

	public Discipline() {
	}

	public Discipline(Long id, String name, String descption, Sport sport, Genre genre) {
		super();
		this.id = id;
		this.name = name;
		this.descption = descption;
		this.sport = sport;
		this.genre = genre;
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

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return String.format(	"Discipline [id=%s, name=%s, descption=%s, sport=%s, genre=%s]", id, name, descption,
								sport, genre);
	}

}
