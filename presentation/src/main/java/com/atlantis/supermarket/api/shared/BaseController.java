package com.atlantis.supermarket.api.shared;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.atlantis.supermarket.core.product.Provider;

// TODO: implementar esto y probarlo
public abstract class BaseController{
   
    //@GetMapping
    public abstract Page<?> get(Pageable pageable);
    
    //@GetMapping("/{identifier}")
    public abstract <T> T get(@PathVariable String identifier);
	
    
    //@PostMapping
    public abstract <T, G> T create(@RequestBody G provider);
    
    //@PutMapping
    public abstract <T, G> T update(@RequestBody G provider);
    
    //@DeleteMapping("/{identifier}")
    public abstract void update(@PathVariable String identifier);
}
