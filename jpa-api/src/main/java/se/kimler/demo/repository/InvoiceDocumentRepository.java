package se.kimler.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import se.kimler.demo.domain.InvoiceDocument;

@Repository
public interface InvoiceDocumentRepository extends PagingAndSortingRepository<InvoiceDocument, Long> {

}
