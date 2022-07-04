package com.bargunyo.alfonso.DiceGame8.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bargunyo.alfonso.DiceGame8.entity.Player;
import com.bargunyo.alfonso.DiceGame8.service.PlayerService;

@RestController
public class PlayerController {

	@Autowired
	PlayerService playerService;

	    //get players
		@GetMapping("players/getAll")
		public ResponseEntity<?> getAll() {

			playerService.updatePercentage();

			return (ResponseEntity.status(HttpStatus.OK)).body(playerService.dataPlayers(playerService.getAllPlayers()));
		}

		//add player
		@PostMapping("players/add")
		public ResponseEntity<?> savePlayer(@RequestBody Player player) {

			if (!playerService.playerExistsByName(player)) {

				Player newPlayer = new Player(player.getName());

				return ResponseEntity.status(HttpStatus.CREATED).body(playerService.addPlayer(newPlayer));
			} else {

				return (ResponseEntity.status(HttpStatus.BAD_REQUEST)).body("This player Already exists.");
			}
		}
		
		//update player
		@PutMapping("/players/{id}")
		public ResponseEntity<?> updatePlayer(@RequestBody Player player, @PathVariable(value = "id") Long id)
				throws Exception {

			Player playerFound = playerService.getPlayerById(id);

			if (player.getName() != null && playerService.playerExistsByName(player)) {

				playerFound.setName(player.getName());

				return ResponseEntity.status(HttpStatus.OK).body(playerService.updatePlayer(playerFound));
			} else {

				return (ResponseEntity.status(HttpStatus.BAD_REQUEST)).body("This name already exists.");
			}

		}
		//Get player by Id
		@GetMapping("/players/{id}")
		public ResponseEntity<?> getPlayerById (@PathVariable (value="id") Long id) throws Exception {
			
			return ResponseEntity.status(HttpStatus.OK).body(playerService.getPlayerById(id));
		}

		@GetMapping("/players/ranking")
		public ResponseEntity<?> getAverageRanking() {

			playerService.getAverageRanking();

			return ResponseEntity.status(HttpStatus.OK)
					.body("The average success rate of all players is " + playerService.getAverageRanking() + " %");
		}

		@GetMapping("/players/ranking/loser")
		public ResponseEntity<?> getWorstPlayer() {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.getWorstPlayer(playerService.getAllPlayers()));
		}

		@GetMapping("/players/ranking/winner")
		public ResponseEntity<?> getBestPlayer() {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.getBestPlayer(playerService.getAllPlayers()));
		}

		// delete player
		@DeleteMapping(path = "players/delete/{id}")
		public ResponseEntity<?> deletePlayer(@PathVariable("id") Long id) {
			boolean ok = this.playerService.deletePlayer(id);
			if (ok) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		}

		

}
