package br.com.xrpg.controller;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.xrpg.entity.MasterEntity;
import br.com.xrpg.service.MasterService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/mestres")
@AllArgsConstructor
public class MasterController {

	private final MasterService service;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody MasterEntity master) {

		MasterEntity masterCreated = this.service.create(master);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(masterCreated.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<MasterEntity> update(@PathVariable BigInteger id, @RequestBody MasterEntity master) {
		master.setId(id);

		this.service.update(master);

		return ResponseEntity.ok(master);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MasterEntity> findById(@PathVariable BigInteger id) {

		MasterEntity master = this.service.findById(id);

		return ResponseEntity.ok(master);

	}

	@GetMapping
	public ResponseEntity<List<MasterEntity>> findAll() {
		List<MasterEntity> allMasters = this.service.findAll();
		return ResponseEntity.ok(allMasters);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable BigInteger id){
		this.service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
