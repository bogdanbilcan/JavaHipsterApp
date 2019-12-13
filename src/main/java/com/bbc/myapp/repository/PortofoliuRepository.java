package com.bbc.myapp.repository;
import com.bbc.myapp.domain.Portofoliu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Portofoliu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PortofoliuRepository extends JpaRepository<Portofoliu, Long>, JpaSpecificationExecutor<Portofoliu> {

}
