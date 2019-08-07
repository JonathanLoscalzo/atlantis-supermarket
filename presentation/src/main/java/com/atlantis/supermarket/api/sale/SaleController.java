package com.atlantis.supermarket.api.sale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantis.supermarket.business.product.useCases.addBatch.AddBatchInput;
import com.atlantis.supermarket.business.sale.SaleService;
import com.atlantis.supermarket.business.sale.useCases.generateSale.GenerateSale;
import com.atlantis.supermarket.business.sale.useCases.generateSale.Input;
import com.atlantis.supermarket.business.sale.useCases.generateSale.Output;
import com.atlantis.supermarket.core.payment.PaymentMethod;
import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.sale.dto.SaleDto;
import com.atlantis.supermarket.core.sale.mapper.SaleMapper;
import com.atlantis.supermarket.infrastructure.payment.PaymentMethodRepository;
import com.atlantis.supermarket.security.UserPrincipal;
import com.atlantis.supermarket.security.UserSecurityService;
import com.atlantis.supermarket.security.auth.IAuthenticationFacade;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    GenerateSale generateSale;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    UserSecurityService securityService;

    @Autowired
    PaymentMethodRepository repo;

    @Autowired
    SaleService service;
    
    @Autowired
    SaleMapper saleMapper;

    @GetMapping("/paymentMethods")
    public List<PaymentMethod> generate() {
	return repo.findAll();
    }

    @PostMapping()
    public Output generate(@RequestBody Input input) {
	UserPrincipal user = (UserPrincipal) securityService
		.loadUserByUsername(authenticationFacade.getAuthentication().getPrincipal().toString());
	input.clientId = user.getUser().getClient().getId().toString();
	return generateSale.handle(input);
    }

    @GetMapping()
    public Page<SaleDto> getSales(Pageable pageable) {
	UserPrincipal user = (UserPrincipal) securityService
		.loadUserByUsername(authenticationFacade.getAuthentication().getPrincipal().toString());
	if (user.getAuthorities().stream().anyMatch(x -> x.getAuthority() == "ADMIN")){
	    return service.find(pageable).map(saleMapper::toDto);
	} else {	    
	    return service.findMySales(user.getUser().getClient(), pageable);
	}
    }

}
