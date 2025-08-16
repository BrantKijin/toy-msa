package sample.catalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sample.catalogservice.jpa.CatalogEntity;
import sample.catalogservice.jpa.CatalogRepository;

@Data
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService{
	CatalogRepository catalogRepository;

	Environment env;

	@Autowired
	public CatalogServiceImpl(CatalogRepository catalogRepository, Environment env) {
		this.catalogRepository = catalogRepository;
		this.env = env;
	}

	@Override
	public Iterable<CatalogEntity> getAllCatalogs() {
		return catalogRepository.findAll();
	}
}
