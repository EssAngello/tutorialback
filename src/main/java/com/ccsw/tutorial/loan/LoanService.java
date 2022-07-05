package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import org.springframework.data.domain.Page;

import java.sql.Date;
import java.util.List;

public interface LoanService {

    /**
     * Recupera un listado de prestamos
     * @return
     */
    List<Loan> findAll();

    /**
     * Recupera un {@link com.ccsw.tutorial.loan.model.Loan} a través de su ID
     * @param id
     * @return
     */
    Loan get(Long id);

    /**
     * Método para recuperar un listado paginado de {@link com.ccsw.tutorial.loan.model.Loan}
     * @param dto
     * @param idGame
     * @param idClient
     * @param date
     * @return
     */
    Page<Loan> findPage(LoanSearchDto dto, Long idGame, Long idClient, Date date);

    /**
     * Método para crear o actualizar un {@link com.ccsw.tutorial.loan.model.Loan}
     * @param id
     * @param data
     */
    void save(Long id, LoanDto data);

    /**
     * Método para eliminar un {@link com.ccsw.tutorial.loan.model.Loan}
     * @param id
     */
    void delete(Long id);

}

