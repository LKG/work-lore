package im.heart.demo;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigInteger;

public interface CustomerProjection {
    @Value("#{target.firstName + ' ' +target.id + ' '+ target.lastName}")
    String getFullName();
    Long getId();

    String getFirstName();
    String getLastName();
}