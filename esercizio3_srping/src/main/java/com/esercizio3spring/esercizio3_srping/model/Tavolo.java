package com.esercizio3spring.esercizio3_srping.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
@Scope("prototype")
public class Tavolo {

	private int coperti, numero;
	private boolean occupato;

	@Autowired
	public Tavolo(@Value("6") int coperti, @Qualifier("getNumeroTavolo") int numero, @Value("false") boolean occupato) {
		super();
		this.coperti = coperti;
		this.numero = numero;
		this.occupato = occupato;
	}

}
