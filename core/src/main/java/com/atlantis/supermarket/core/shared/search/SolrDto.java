package com.atlantis.supermarket.core.shared.search;
//https://dzone.com/articles/complimenting-a-rdbms-with-solr
//https://dzone.com/articles/crud-application-using-spring-data-solr-and-spring
import java.util.UUID;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument
public class SolrDto {
    
    @Field
    private String id; // este es UUID
    
    @Field
    private String name;
   
    public String getId() {
        return id;
    }
    
    public void setId(Class<? extends SolrIndexed>clazz, UUID id){
        this.id = new SolrId(clazz, id).toString();
   }
}
