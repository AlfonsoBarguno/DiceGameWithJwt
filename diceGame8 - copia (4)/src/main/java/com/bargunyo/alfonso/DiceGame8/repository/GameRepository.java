package com.bargunyo.alfonso.DiceGame8.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bargunyo.alfonso.DiceGame8.entity.Game;
import com.bargunyo.alfonso.DiceGame8.entity.Player;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	
	List<Game> findAllByPlayer(Player player);
}
