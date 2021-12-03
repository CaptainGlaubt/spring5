package se.lernholt.tacos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class Order implements Serializable {
    
    private Long       id;
    private Date       placedAt;
    private String     deliveryName;
    private String     deliveryStreet;
    private String     deliveryCity;
    private String     deliveryState;
    private String     deliveryZip;
    private String     ccNumber;
    private String     ccExpiration;
    private String     ccCVV;
    private List<Taco> tacos;
    private User       user;
}
