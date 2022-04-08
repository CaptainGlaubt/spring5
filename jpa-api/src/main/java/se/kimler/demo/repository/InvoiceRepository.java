package se.kimler.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import se.kimler.demo.domain.Invoice;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {

}
