package com.atlantis.supermarket.core.shared.search;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.atlantis.supermarket.core.product.search.ProductSolrDto;

/**
 * default class
 * @author jloscalzo
 *
 */
public class SolrUpdate {

    @Autowired
    SolrClient client;
    
    @Autowired
    SolrCrudRepository<ProductSolrDto,String> repo;
    
    @PostPersist
    @PostUpdate
    public void onCreateUpdate(SolrIndexed indexed) throws java.io.IOException, SolrServerException {
	repo.save((ProductSolrDto) indexed.getSolrDto());
    }

    @PostRemove
    public void onDelete(SolrIndexed indexed) throws java.io.IOException, SolrServerException {
	repo.deleteById(indexed.getSolrDto().getId());
    }
}
