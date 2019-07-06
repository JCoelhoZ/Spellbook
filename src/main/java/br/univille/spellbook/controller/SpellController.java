package br.univille.spellbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.spellbook.model.Spell;
import br.univille.spellbook.service.SpellService;

@Controller
@RequestMapping("/spell")
public class SpellController {
	
	@Autowired
	private SpellService service;
	
	@GetMapping("")
	public ModelAndView index() {
		return new ModelAndView("spell/index", "spells", service.getAll());
	}
	
	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Spell spell) {
		return new ModelAndView("spell/form");
	}
	
	@PostMapping(params="form")
	public ModelAndView save(@Valid Spell spell) {
		service.save(spell);
		return new ModelAndView("redirect:/spell");
	}
	
	@GetMapping(value="/alterar/{id}")
	public ModelAndView edit(@PathVariable("id") Spell spell) {
		return new ModelAndView("spell/form","spell", spell);
	}
	
	@GetMapping(value="/excluir/{id}")
	public ModelAndView delete(@PathVariable("id") Spell spell) {
		service.delete(spell);
		return new ModelAndView("redirect:/spell");
	}
}
