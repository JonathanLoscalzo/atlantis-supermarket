package com.atlantis.supermarket.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Category;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.infrastructure.product.CategoryRepository;
import com.atlantis.supermarket.infrastructure.product.ProviderRepository;
import com.github.javafaker.Faker;

@Component
public class SeedCategories {
    
    @Autowired
    CategoryRepository repo;
    
    public void set() {
	List<Category> categories = new ArrayList<>();
	String[] cats = "Almacen,Limpieza,Electro,Otros,Herramientas".split(",");
	
	if (repo.findAll().size() == 0) {
	    for (int i = 0; i < cats.length; i++) {
		Faker faker = new Faker(new Locale("es-ES"));
		Category c = new Category();
		c.setDescription(cats[i]);
		categories.add(c);
	    }
	   repo.saveAll(categories);
	}
    }
}
