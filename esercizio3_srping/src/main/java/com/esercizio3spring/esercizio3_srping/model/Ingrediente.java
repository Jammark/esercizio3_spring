package com.esercizio3spring.esercizio3_srping.model;

import lombok.Getter;

@Getter
public class Ingrediente extends Consumation {

	private double price;
	private int calorie;

	public Ingrediente(String name, double price, int calorie) {
		super(name);
		this.price = price;
		this.calorie = calorie;

	}

	@Override
	public double getPrice() {
		return this.price;
	}

}
