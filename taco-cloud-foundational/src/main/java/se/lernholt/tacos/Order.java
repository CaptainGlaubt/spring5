package se.lernholt.tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name = "Taco_Order")
@SuppressWarnings("serial")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;
    @NotBlank(message = "Name is required.")
    private String deliveryName;
    @NotBlank(message = "Street is required.")
    private String deliveryStreet;
    @NotBlank(message = "City is required.")
    private String deliveryCity;
    @NotBlank(message = "State is required.")
    private String deliveryState;
    @NotBlank(message = "Zip code is required.")
    private String deliveryZip;
    @CreditCardNumber(message = "Not a valid credit card number.")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CCV")
    private String ccCVV;
    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos;
    @ManyToOne
    private User user;

    public void addDesign(Taco taco) {
        if (Objects.isNull(tacos)) {
            tacos = new ArrayList<>();
        }
        tacos.add(taco);
    }

    @PrePersist
    public void placedAt() {
        placedAt = new Date();
    }
}
