package com.atlantis.supermarket.api.client;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atlantis.supermarket.api.shared.BaseController;
import com.atlantis.supermarket.business.client.ClientService;
import com.atlantis.supermarket.business.client.useCases.input.CreateClientByUser;
import com.atlantis.supermarket.business.product.ProductService;
import com.atlantis.supermarket.business.product.useCases.createProduct.CreateProduct;
import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.client.dto.ClientDto;
import com.atlantis.supermarket.core.client.mapper.ClientMapper;
import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;
import com.atlantis.supermarket.core.user.exceptions.UsernameNotFoundException;
import com.atlantis.supermarket.security.UserPrincipal;
import com.atlantis.supermarket.security.auth.IAuthenticationFacade;

@RestController
@RequestMapping("/api/client")
public class ClientController extends BaseController {
    @Autowired
    private CreateProduct createProduct;
    
    @Autowired private ClientService service;
    
    
    @Autowired private ClientMapper mapper;
   
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    
    @GetMapping()
    public List<ClientDto> get(){	
	return service.find()
		.stream()
		.map(x -> mapper.toDto(x))
		.collect(Collectors.toList());
    }
    
    
    @PostMapping("/assign")
    public Object assignClientToUser(@RequestBody CreateClientByUser input) {

	input.setUsername((String) this.authenticationFacade.getAuthentication().getPrincipal());

	try {
	    service.createClientByUser(input);
	} catch (UsernameNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	return "";
    }
     
    
    @PostMapping()
    public List<ClientDto> create(){
	return service.find()
		.stream()
		.map(x -> mapper.toDto(x))
		.collect(Collectors.toList());
    }
    
    @PutMapping()
    public List<ClientDto> update(){
	return service.find().stream().map(x -> mapper.toDto(x)).collect(Collectors.toList());
    }
}
