package se.lernholt.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import se.lernholt.tacos.Order;
import se.lernholt.tacos.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByDeliveryZip(String deliveryZip);

    List<Order> findByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

    List<Order> findByDeliveryStreetAndDeliveryCity(String deliveryStreet, String deliveryCity);

    List<Order> findByDeliveryCityOrderByDeliveryStreet(String city);

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

    @Query("from Order o where o.deliveryCity='Seattle'")
    List<Order> readOrdersDeliveredInSeattle();
}
