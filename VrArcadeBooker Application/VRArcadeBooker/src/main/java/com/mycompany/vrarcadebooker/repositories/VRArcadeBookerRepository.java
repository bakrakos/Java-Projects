package com.mycompany.vrarcadebooker.repositories;

import com.mycompany.vrarcadebooker.entity.VrBooker;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Add SpringDataJPA to your ojt reflection registration web application.
 *
 * @author OB
 * @since 20200606
 */


@Repository
public interface VRArcadeBookerRepository extends CrudRepository<VrBooker, Integer> {

    ArrayList<VrBooker> findAllBydateOfBooking(String dateOfBooking);
}
