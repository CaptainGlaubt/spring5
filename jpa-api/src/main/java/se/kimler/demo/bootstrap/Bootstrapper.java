package se.kimler.demo.bootstrap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import se.kimler.demo.repository.InvoiceRepository;

@Component
@RequiredArgsConstructor
public class Bootstrapper {
    
    
    private final InvoiceRepository invoiceRepository;

    @PostConstruct
    public void bootstrap() {
        
    }
    
    private static void createFiles() {
        
    }
    
    
}
