package com.esercizio3spring.esercizio3_srping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.esercizio3spring.esercizio3_srping.model.Bevanda;
import com.esercizio3spring.esercizio3_srping.model.Ingrediente;
import com.esercizio3spring.esercizio3_srping.model.Ordine;
import com.esercizio3spring.esercizio3_srping.model.PizzaDecorator;
import com.esercizio3spring.esercizio3_srping.model.ToppingDecorator;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PizzeriaRunner implements CommandLineRunner {

	private AppConfig config;

	@Autowired
	public PizzeriaRunner(AppConfig config) {
		super();
		this.config = config;
	}

	@Override
	public void run(String... args) throws Exception {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				Esercizio3SrpingApplication.class);

		List<PizzaDecorator> pizze = Arrays.asList(new PizzaDecorator[] { (PizzaDecorator) ctx.getBean("getHawaiana"),
				(PizzaDecorator) ctx.getBean("getCottoEFunghi"), (PizzaDecorator) ctx.getBean("getDiavola"),
				(PizzaDecorator) ctx.getBean("getMaisECotto") });
		log.info(addSpaces("Pizze", 100) + addSpaces("Calorie", 20) + addSpaces("Prezzo", 20));
		pizze.forEach(p -> log.info(getPizza(p)));
		List<Ingrediente> ingredienti = Arrays.asList(new Ingrediente[] { (Ingrediente) ctx.getBean("getAnanas"),
				(Ingrediente) ctx.getBean("getProsciutto"), (Ingrediente) ctx.getBean("getFunghi"),
				(Ingrediente) ctx.getBean("getMais"), (Ingrediente) ctx.getBean("getSalame") });
		log.info("---------------------");
		log.info(addSpaces("Ingredienti", 120) + addSpaces("Prezzo", 20));
		ingredienti.forEach(i -> log.info(getIngrediente(i)));
		log.info("---------------------");
		log.info(addSpaces("Bevande", 120) + addSpaces("Prezzo", 20));
		Arrays.asList(new Bevanda[] { (Bevanda) ctx.getBean("getAcqua"), (Bevanda) ctx.getBean("getCola"),
				(Bevanda) ctx.getBean("getBirra"), }).stream().forEach(b -> log.info(getBev(b)));

		// Tavolo t = new Tavolo(4, 3, false);
		/*
		 * Ordine o = new Ordine(LocalDate.now(), new ArrayList<>(Arrays.asList( new
		 * ToppingDecorator((PizzaDecorator) ctx.getBean("getHawaiana"), (Ingrediente)
		 * ctx.getBean("getSalame")), (PizzaDecorator) ctx.getBean("getCottoEFunghi"),
		 * (Bevanda) ctx.getBean("getBirra"))), t, 10, 2);
		 */
		config.setNextConsumations(new ArrayList<>(Arrays.asList(
				new ToppingDecorator((PizzaDecorator) ctx.getBean("getHawaiana"),
						(Ingrediente) ctx.getBean("getSalame")),
				(PizzaDecorator) ctx.getBean("getCottoEFunghi"), (Bevanda) ctx.getBean("getBirra"))));
		Ordine o1 = ctx.getBean(Ordine.class);

		log.info("Nuovo ordine : " + o1.toString());
		ctx.close();

	}

	private String getPizza(PizzaDecorator p) {
		String s = p.toString();
		s = addSpaces(s, 100);
		String c = "" + p.getCalorie();
		c = addSpaces(c, 20);
		String pr = "" + p.getPrice();
		pr = addSpaces(pr, 20);
		return s + c + pr;
	}

	private String getIngrediente(Ingrediente i) {
		String s = i.getName();
		s = addSpaces(s, 120);
		String p = "" + i.getPrice();
		p = addSpaces(p, 20);
		return s + p;
	}

	private String getBev(Bevanda i) {
		String s = i.getName();
		s = addSpaces(s, 120);
		String p = "" + i.getPrice();
		p = addSpaces(p, 20);
		return s + p;
	}

	private String addSpaces(String s, int l) {
		int sl = s.length();
		if (s.length() < l) {
			for (int i = 0; i < l - sl; i++) {
				s += " ";
			}
		}
		return s;
	}
}
