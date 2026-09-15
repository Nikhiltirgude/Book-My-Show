package org.bookmyshow.demo.Repository;

import org.bookmyshow.demo.Entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Integer> {
}
