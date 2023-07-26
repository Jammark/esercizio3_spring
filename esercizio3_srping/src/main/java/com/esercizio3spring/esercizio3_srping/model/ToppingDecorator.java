package com.esercizio3spring.esercizio3_srping.model;

public class ToppingDecorator extends IngredienteDecorator {

	public ToppingDecorator(Consumation campo, Ingrediente ingrediente) {
		super(campo, ingrediente);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getCampo().getName() + " + " + getIngrediente().getName();
	}
}
