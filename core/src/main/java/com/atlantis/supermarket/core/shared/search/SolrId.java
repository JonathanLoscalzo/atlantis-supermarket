package com.atlantis.supermarket.core.shared.search;

import java.util.UUID;

public class SolrId {
    private final Class<? extends SolrIndexed> entityClass;
    private final UUID id;

    /**
     * Crea un solr id para indexar una clase que se encuentra en el MySQL
     * 
     * @param clazz
     * @param id
     */
    public SolrId(Class<? extends SolrIndexed> clazz, UUID id) {
	this.entityClass = clazz;
	this.id = id;
    }

    /**
     * Recupera del solrId, la clase y el id correspondiente a MySQL
     * 
     * @param solrId
     */
    public SolrId(String solrId) {
	int dotPos = solrId.lastIndexOf('.');
	if (dotPos == -1) {
	    throw new RuntimeException("Invalid SolrId:  " + solrId);
	}
	try {
	    this.entityClass = getClassFromId(solrId, dotPos);
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException("SolrId has invalid class: " + solrId);
	}

	this.id = UUID.fromString(solrId.substring(dotPos + 1));
    }

    private Class<? extends SolrIndexed> getClassFromId(String solrId, int dotPos) throws ClassNotFoundException {
	return (Class<? extends SolrIndexed>) Class.forName(solrId.substring(0, dotPos));
    }

    public Class<? extends SolrIndexed> getEntityClass() {
	return entityClass;
    }

    public UUID getId() {
	return id;
    }

    @Override
    public String toString() {
	return entityClass.getName() + "." + id.toString();
    }
}
