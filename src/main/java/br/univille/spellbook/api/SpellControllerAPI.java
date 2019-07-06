package br.univille.spellbook.api;

import java.util.ArrayList;
import java.util.List;

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

import br.univille.spellbook.model.Spell;
import br.univille.spellbook.service.SpellService;

@RestController
@RequestMapping("/api/spells")
public class SpellControllerAPI {
	@Autowired
	private SpellService service;
	
	@GetMapping
	public ResponseEntity<List<Spell>> listarSpells(){
		List<Spell> lista = new ArrayList<Spell>();
		try {
			lista = service.getAll();
		}catch (Exception e){
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return new ResponseEntity<List<Spell>>(lista, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Spell spell){
		service.save(spell);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Spell spell){
		Spell spellAtual = service.findById(id);
		if(spellAtual != null) {
			spellAtual.setSpellName(spell.getSpellName());
			spellAtual.setIncantation(spell.getIncantation());
			spellAtual.setDescription(spell.getDescription());
			spellAtual.setType(spell.getType());
			service.save(spellAtual);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping(path="/{id")
	public ResponseEntity<?> delete(@PathVariable("id") long id){
		Spell spellAtual = service.findById(id);
		if(spellAtual != null) {
			service.delete(spellAtual);
			return ResponseEntity.ok().build();
		}
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
