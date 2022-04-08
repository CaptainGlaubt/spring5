package se.kimler.demo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                   id;
    @Column(name = "created_at", nullable = false)
    private Date                      createdAt;
    @Column(name = "file_name", nullable = false)
    private String                    fileName;
    @Column(name = "cost_center", nullable = false)
    private String                    costCenter;
    @Column(name = "personal_identifier", nullable = false)
    private String                    personalIdentifier;
    @Column(name = "mail_box_operator", nullable = false)
    private String                    mailBoxOperator;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Distribution              distribution;

    @OneToMany(targetEntity = InvoiceDocument.class, cascade = CascadeType.ALL, mappedBy = "invoice")
    private List<InvoiceDocument> documents;

    public static enum Distribution {
        EMAIL, MAIL, WORD_OF_MOUTH;
    }
}
