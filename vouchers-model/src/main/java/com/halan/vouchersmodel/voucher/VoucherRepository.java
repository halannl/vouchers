package com.halan.vouchersmodel.voucher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends MongoRepository<Voucher, String> {
    List<Voucher> findByUsuarioEmail(String email);
}
