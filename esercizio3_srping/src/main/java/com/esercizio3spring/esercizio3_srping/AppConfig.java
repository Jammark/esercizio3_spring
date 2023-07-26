package com.esercizio3spring.esercizio3_srping;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.esercizio3spring.esercizio3_srping.model.Bevanda;
import com.esercizio3spring.esercizio3_srping.model.Consumation;
import com.esercizio3spring.esercizio3_srping.model.Ingrediente;
import com.esercizio3spring.esercizio3_srping.model.IngredienteDecorator;
import com.esercizio3spring.esercizio3_srping.model.Pizza;

import lombok.Setter;

@Configuration
@PropertySource("classpath:application.properties")
@Setter
@Scope("singleton")

public class AppConfig {

	private static int tavoliCount = 0, ordiniCount = 0;

	private List<Consumation> nextConsumations;

	@Value("${pomodoro}")
	private double prezzoPomodoro;

	@Value("${mozzarella}")
	private double prezzoMozzarella;

	@Value("${ananas}")
	private double prezzoAnanas;

	@Value("${prosciutto}")
	private double prezzoProsciutto;

	@Value("${funghi}")
	private double prezzoFunghi;

	@Value("${salame}")
	private double prezzoSalame;

	@Value("${mais}")
	private double prezzoMais;

	@Value("${acqua}")
	private double prezzoAcqua;

	@Value("${cola}")
	private double prezzoCola;

	@Value("${birra}")
	private double prezzoBirra;

	@Bean
	public Ingrediente getPomodoro() {
		return new Ingrediente("Pomodoro", this.prezzoPomodoro, 100);
	}

	@Bean
	public Ingrediente getMozzarella() {
		return new Ingrediente("Mozzarella", this.prezzoMozzarella, 140);
	}

	@Bean
	public Ingrediente getAnanas() {
		return new Ingrediente("Ananas", this.prezzoAnanas, 200);
	}

	@Bean
	public Ingrediente getProsciutto() {
		return new Ingrediente("Prosciutto cotto", this.prezzoProsciutto, 300);
	}

	@Bean
	public Ingrediente getFunghi() {
		return new Ingrediente("funghi", this.prezzoFunghi, 400);
	}

	@Bean
	public Ingrediente getMais() {
		return new Ingrediente("mais", this.prezzoMais, 400);
	}

	@Bean
	public Ingrediente getSalame() {
		return new Ingrediente("salame piccante", this.prezzoSalame, 500);
	}

	@Bean
	public Bevanda getCola() {
		return new Bevanda("Pepsi", this.prezzoCola);
	}

	@Bean
	public Bevanda getAcqua() {
		return new Bevanda("Acqua Minerale", this.prezzoAcqua);
	}

	@Bean
	public Bevanda getBirra() {
		return new Bevanda("Heineken", this.prezzoBirra);
	}

	@Bean
	@Scope("prototype")
	public Consumation getHawaiana() {
		return new IngredienteDecorator(new IngredienteDecorator(new IngredienteDecorator(
				new IngredienteDecorator(new Pizza("Hawaiana", 1200), getAnanas()), getProsciutto()), getPomodoro()),
				getMozzarella());
	}

	@Bean
	@Scope("prototype")
	public Consumation getCottoEFunghi() {
		return new IngredienteDecorator(new IngredienteDecorator(
				new IngredienteDecorator(new IngredienteDecorator(new Pizza("Cotto e funghi", 900), getFunghi()),
						getProsciutto()),
				getPomodoro()), getMozzarella());

	}

	@Bean
	@Scope("prototype")
	public Consumation getDiavola() {
		return

		new IngredienteDecorator(new IngredienteDecorator(
				new IngredienteDecorator(new Pizza("Diavola", 1100), getSalame()), getPomodoro()), getMozzarella());

	}

	@Bean
	@Scope("prototype")
	public Consumation getMaisECotto() {
		return new IngredienteDecorator(new IngredienteDecorator(new IngredienteDecorator(
				new IngredienteDecorator(new Pizza("Mais e cotto", 800), getMais()), getProsciutto()), getPomodoro()),
				getMozzarella());

	}

	@Bean
	public int getNumeroTavolo() {
		return ++tavoliCount;
	}

	@Bean
	public int getNumeroOrdine() {
		return ++ordiniCount;
	}

	@Bean
	public LocalDate getAcquisizione() {
		return LocalDate.now();
	}

	@SuppressWarnings("unchecked")
	@Bean
	public List<Consumation> getNextConsumations() {
		if (this.nextConsumations != null) {
			return this.nextConsumations;
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	@Bean
	public int getCoperti() {
		return new Random().nextInt(1, 7);
	}
}
