package com.halan.vouchersrepository.voucher;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoucherRepository extends MongoRepository<Voucher, String> {
}
