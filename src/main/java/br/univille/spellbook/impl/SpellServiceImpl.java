package br.univille.spellbook.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.spellbook.model.Spell;
import br.univille.spellbook.repository.SpellRepository;
import br.univille.spellbook.service.SpellService;
@Service
public class SpellServiceImpl implements SpellService{
	
	@Autowired
	private SpellRepository spellRepository;

	@Override
	public List<Spell> getAll() {
		return spellRepository.findAll();
	}

	@Override
	public void save(Spell spell) {
		spellRepository.save(spell);
	}

	@Override
	public void delete(Spell spell) {
		spellRepository.delete(spell);
	}

	@Override
	public Spell findById(long id) {
		Optional<Spell> retorno = spellRepository.findById(id);
		if (retorno.isPresent()) {
			return retorno.get();
		}
		return null;
	}
	
	
	
	
	
}
