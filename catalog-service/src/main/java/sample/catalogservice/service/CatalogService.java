package sample.catalogservice.service;

import sample.catalogservice.jpa.CatalogEntity;

public interface CatalogService {
	Iterable<CatalogEntity> getAllCatalogs();
}
