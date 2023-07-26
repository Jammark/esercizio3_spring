package com.esercizio3spring.esercizio3_srping.model;

public class Bevanda extends Consumation {

	private double price;

	public Bevanda(String name, double price) {
		super(name);
		this.price = price;
	}

	@Override
	public double getPrice() {

		return this.price;
	}

	@Override
	public String toString() {
		return "Bevanda [price=" + price + ", getName()=" + getName() + "]";
	}

}
