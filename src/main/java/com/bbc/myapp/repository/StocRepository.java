package com.bbc.myapp.repository;
import com.bbc.myapp.domain.Stoc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Stoc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StocRepository extends JpaRepository<Stoc, Long>, JpaSpecificationExecutor<Stoc> {

}
