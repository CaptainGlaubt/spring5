package com.lernholt.domain.user;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("user")
@Document(collection = "users")
public class User {

    @Id
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private final UUID          id        = Uuids.timeBased();
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private final Date          createdAt = new Date();
    private String              username;
    private String              firstName;
    private String              middleName;
    private String              lastName;
    @Column("contact_info")
    private ContactInfoUDT      contactInfo;
    @Column("address_info")
    private AddressInfoUDT      addressInfo;
    @Column("payment_info")
    private PaymentInfoUDT      paymentInfo;
    @Column("registration_info")
    private RegistrationInfoUDT registrationInfo;

    public UserDetails toUserDetails() {
        return new org.springframework.security.core.userdetails.User(id.toString(), firstName, null);
    }
}
