package br.univille.spellbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.spellbook.model.Spell;

public interface SpellRepository extends JpaRepository<Spell, Long>{
	Spell findById(String name);
}
