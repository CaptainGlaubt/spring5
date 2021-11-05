package se.lernholt.controller;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import se.lernholt.repository.jpa.OrderRepository;
import se.lernholt.tacos.Order;

@RestController
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    @PutMapping("/{id}")
    public Order putOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Order> patchOrder(@PathVariable("id") Long id, @RequestBody Order patchOrder) {
        Optional<Order> storedOrderOpt = orderRepository.findById(id);
        if (storedOrderOpt.isPresent()) {
            Order storedOrder = storedOrderOpt.get();
            patchIfNotNull(patchOrder::getDeliveryName, storedOrder::setDeliveryName);
            patchIfNotNull(patchOrder::getDeliveryStreet, storedOrder::setDeliveryStreet);
            patchIfNotNull(patchOrder::getDeliveryCity, storedOrder::setDeliveryCity);
            patchIfNotNull(patchOrder::getDeliveryState, storedOrder::setDeliveryState);
            patchIfNotNull(patchOrder::getDeliveryZip, storedOrder::setDeliveryZip);
            patchIfNotNull(patchOrder::getCcNumber, storedOrder::setCcNumber);
            patchIfNotNull(patchOrder::getCcExpiration, storedOrder::setCcExpiration);
            patchIfNotNull(patchOrder::getCcCVV, storedOrder::setCcCVV);
            orderRepository.save(storedOrder);
            return ResponseEntity.ok(storedOrder);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private static <T> void patchIfNotNull(Supplier<T> patchValueSupplier, Consumer<T> patchValueConsumer) {
        T value = patchValueSupplier.get();
        if (Objects.nonNull(value)) {
            patchValueConsumer.accept(value);
        }
    }
}
