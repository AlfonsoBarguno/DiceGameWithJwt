package com.bargunyo.alfonso.DiceGame8.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bargunyo.alfonso.DiceGame8.entity.Player;


public interface PlayerRepository extends JpaRepository<Player, Long>{

}
