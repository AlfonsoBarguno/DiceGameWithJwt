package com.bargunyo.alfonso.DiceGame8.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Pk_PlayerId")
	private Long idPlayer;

	@Column(name = "name")
	private String name;

	@Column(name = "date")
	private Date date;

	@Column(name = "victory_percentage")
	private double victoryPercentage;

	@OneToMany(mappedBy = "player")
	private List<Game> games;

	public Player() {

	}

	public Player(String name) {
		this.name = name;

		this.date = new Date();

		this.games = null;

	}

	public double successPercentage(List<Game> games) {
		double totalGames = games.size();
		double victorys = 0;

		for (int i = 0; i < totalGames; i++) {
			if (games.get(i).isWin()) {
				victorys += 1;
			}
		}
		if (victorys > 0) {
			double percentatge = victorys / totalGames * 100;

			return percentatge;
		} else {
			return 0;
		}
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
		games.forEach(game -> game.setPlayer(this));
	}

	public void addGame(Game game) {

		games.add(game);
	}

	public Long getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Long idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getVictoryPercentage() {
		return victoryPercentage;
	}

	public void setVictoryPercentage(double victoryPercentage) {
		this.victoryPercentage = victoryPercentage;
	}

}
