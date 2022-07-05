package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.loan.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    /**
     * Método para recuperar un listado paginado de {@link com.ccsw.tutorial.loan.model.Loan}
     * @param pageable
     * @return
     */
    @Query("select l from Loan l where (:game is null or l.game.id = :game) and (:client is null or l.client.id = :client) and (:date is null or l.startDate<=:date and l.endDate>=:date)" )
    Page<Loan> findAll(Pageable pageable, Long game, Long client, Date date);

}
