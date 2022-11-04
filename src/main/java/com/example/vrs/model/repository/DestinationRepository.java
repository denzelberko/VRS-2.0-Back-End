package com.example.vrs.model.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.vrs.model.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {


    @Query(value = "select * from destinations d " +
            "where d.priceIndex = :priceIndex and d.purpose = :purpose and d.weather = :weather" , nativeQuery = true)
    List<Destination> search(@Param("priceIndex") Long priceIndex, @Param("purpose") String purpose, @Param("weather") String weather);
}
