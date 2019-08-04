package com.atlantis.supermarket.business.product;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.ServiceImpl;
import com.atlantis.supermarket.core.product.Category;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.product.exception.ProductNotFoundException;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;
import com.atlantis.supermarket.core.product.search.ProductSolrDto;
import com.atlantis.supermarket.core.product.search.SearchProduct;
import com.atlantis.supermarket.core.shared.EntityNotFoundException;
import com.atlantis.supermarket.core.shared.search.SolrId;
import com.atlantis.supermarket.infrastructure.product.CategoryRepository;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;
import com.atlantis.supermarket.infrastructure.product.search.ProductSolrRepository;

@Service
public class ProductService extends ServiceImpl<Product> {

    private CategoryRepository catRepo;

    private ProductSolrRepository solrRepo;

    private ProductMapper mapper;

    public ProductService(ProductRepository repo,
	    CategoryRepository catRepo,
	    ProductSolrRepository solrRepo,
	    ProductMapper mapper) {
	super(repo);
	this.catRepo = catRepo;
	this.solrRepo = solrRepo;
	this.mapper = mapper;
    }

    /**
     * use CreateProductUseCase
     */
    @Override
    public Product save(Product entity) {
	return null;
    }

    public Page<ProductDto> findByPattern(SearchProduct search, Pageable pageable) {
	// HighlightPage<ProductSolrDto> productsSolr =
	// this.solrRepo.findByNameLike(search.name, pageable);
	HighlightPage<ProductSolrDto> productsSolr = this.solrRepo.findByNameLikeAndRetailPriceBetween(search.name,
		search.minPrice, search.maxPrice, pageable);

	List<Product> products = this.repo.findAllById(
		productsSolr.map(x -> new SolrId(x.getId()).getId())
			.stream()
			.collect(Collectors.toList()))
		.stream()
		// .filter(p -> p.isPurchable())
		.collect(Collectors.toList());

	// temita acá para las búsquedas!
	return productsSolr.map(x -> mapper.toDto(x))
		.map(x -> {
		    return products
			    .stream()
			    .filter(p -> p.getId().equals(UUID.fromString(x.getId())))
			    .findAny().get();
		})
		.map(x -> mapper.toDto(x));
    }

    public Product addCategory(UUID category, UUID product) {
	Product p = this.repo.findById(product).orElseThrow(() -> new ProductNotFoundException(product));
	Category c = this.catRepo.findById(category)
		.orElseThrow(() -> new EntityNotFoundException("nose", "Category", category.toString()));

	p.addCategory(c);
	this.repo.save(p);
	return p;

    }
}
