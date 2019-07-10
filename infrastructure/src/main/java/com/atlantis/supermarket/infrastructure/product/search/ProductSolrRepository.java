package com.atlantis.supermarket.infrastructure.product.search;

import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.Query.Operator;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.atlantis.supermarket.core.product.search.ProductSolrDto;

public interface ProductSolrRepository extends SolrCrudRepository<ProductSolrDto, String>{
	@Query(fields = { "id", "name", "brand", "description", "categories", "retailPrice" }, defaultOperator = Operator.AND)
	HighlightPage<ProductSolrDto> findByNameLikeAndRetailPriceBetween(String name, Long minPrice, Long maxPrice, Pageable page);
	
	@Query(fields = { "id", "name", "brand", "description", "categories", "retailPrice" }, defaultOperator = Operator.AND)
	HighlightPage<ProductSolrDto> findByNameLike(String name, Pageable page);
}
