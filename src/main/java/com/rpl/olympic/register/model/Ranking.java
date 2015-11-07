package com.rpl.olympic.register.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RANKING")
public class Ranking implements Serializable {

	private static final long	serialVersionUID	= -2673639355613094823L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long				id;
	private Discipline			discipline;
	private Sport				sport;
	private Contry				goldenContry;
	private Contry				silverContry;
	private Contry				bronzeContry;
	private Athlete				goldAthlete;
	private Athlete				silverAthlete;
	private Athlete				bronzeAthlete;

	public Ranking() {
	}

	public Ranking(Long id, Discipline discipline, Sport sport, Contry goldenContry, Contry silverContry,
			Contry bronzeContry, Athlete goldAthlete, Athlete silverAthlete, Athlete bronzeAthlete) {
		super();
		this.id = id;
		this.discipline = discipline;
		this.sport = sport;
		this.goldenContry = goldenContry;
		this.silverContry = silverContry;
		this.bronzeContry = bronzeContry;
		this.goldAthlete = goldAthlete;
		this.silverAthlete = silverAthlete;
		this.bronzeAthlete = bronzeAthlete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Contry getGoldenContry() {
		return goldenContry;
	}

	public void setGoldenContry(Contry goldenContry) {
		this.goldenContry = goldenContry;
	}

	public Contry getSilverContry() {
		return silverContry;
	}

	public void setSilverContry(Contry silverContry) {
		this.silverContry = silverContry;
	}

	public Contry getBronzeContry() {
		return bronzeContry;
	}

	public void setBronzeContry(Contry bronzeContry) {
		this.bronzeContry = bronzeContry;
	}

	public Athlete getGoldAthlete() {
		return goldAthlete;
	}

	public void setGoldAthlete(Athlete goldAthlete) {
		this.goldAthlete = goldAthlete;
	}

	public Athlete getSilverAthlete() {
		return silverAthlete;
	}

	public void setSilverAthlete(Athlete silverAthlete) {
		this.silverAthlete = silverAthlete;
	}

	public Athlete getBronzeAthlete() {
		return bronzeAthlete;
	}

	public void setBronzeAthlete(Athlete bronzeAthlete) {
		this.bronzeAthlete = bronzeAthlete;
	}

	@Override
	public String toString() {
		return String.format(	"Ranking [id=%s, discipline=%s, sport=%s, goldenContry=%s, silverContry=%s, bronzeContry=%s]",
								id, discipline, sport, goldenContry, silverContry, bronzeContry);
	}

}
