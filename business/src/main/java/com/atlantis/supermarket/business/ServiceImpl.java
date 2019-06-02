package com.atlantis.supermarket.business;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantis.supermarket.core.shared.BaseEntity;
import com.atlantis.supermarket.core.shared.BaseService;
import com.atlantis.supermarket.core.shared.EntityNotFoundException;

public abstract class ServiceImpl<T extends BaseEntity> implements BaseService<T, UUID>{
    
    protected JpaRepository<T, UUID> repo;
    
    protected ServiceImpl(JpaRepository<T,UUID> repo) {
	this.repo = repo;
    }
    
    @Override
    public T save(T entity) {
	return repo.save(entity);
    }

    @Override
    public Boolean exist(UUID id) {
	return this.repo.existsById(id);
    }

    @Override
    public T retrieve(UUID identifier) {
	return this.repo.findById(identifier).orElseThrow(() -> new EntityNotFoundException("Entity not found", "Class", identifier.toString()));
    }
    
    @Override
    public T retrieve(UUID identifier, Class<?> klass) {
	return this.repo.findById(identifier).orElseThrow(() -> new EntityNotFoundException("Entity not found", klass.getName(), identifier.toString()));
    }

    @Override
    public void delete(UUID identifier) {
	this.repo.findById(identifier).ifPresent(x -> x.setDeleted(true));
    }
    
    @Override
    public Collection<T> find() {
	return this.repo.findAll();
    }
    
    @Override
    public Page<T> find(Pageable pageable) {
	return this.repo.findAll(pageable);
    }
}
