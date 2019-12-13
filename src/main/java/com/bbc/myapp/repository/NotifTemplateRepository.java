package com.bbc.myapp.repository;
import com.bbc.myapp.domain.NotifTemplate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NotifTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotifTemplateRepository extends JpaRepository<NotifTemplate, Long> {

}
