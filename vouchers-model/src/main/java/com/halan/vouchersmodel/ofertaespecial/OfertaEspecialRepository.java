package com.halan.vouchersmodel.ofertaespecial;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaEspecialRepository extends MongoRepository<OfertaEspecial, String> {
}
