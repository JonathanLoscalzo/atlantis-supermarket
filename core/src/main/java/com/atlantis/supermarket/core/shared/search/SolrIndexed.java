package com.atlantis.supermarket.core.shared.search;

public interface SolrIndexed {
    SolrDto getSolrDto();
    /**
     *  
     *   each of these classes is responsible for providing a mapping from the entity to SolrDto
    @Transient
    @Override
    public SolrDto getSolrDto() {
        SolrDto dto = new SolrDto();
        dto.setId(this.getClass(), id);
        dto.setName(name);
        …
        return dto;
     */
}
