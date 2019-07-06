package br.univille.spellbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.spellbook.model.Spell;

@Service
public interface SpellService {
	List<Spell> getAll();
	
	void save(Spell spell);
	
	void delete(Spell spell);
	
	Spell findById(long id);
}
