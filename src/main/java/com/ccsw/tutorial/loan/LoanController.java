package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.config.mapper.BeanMapper;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * @author ccsw
 */
@RequestMapping(value = "/loan")
@RestController
@CrossOrigin(origins = "*")
public class LoanController {
    
    @Autowired
    LoanService loanService;

    @Autowired
    BeanMapper beanMapper;

    /**
     * Recupera un listado de préstamos
     * @return
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<LoanDto> findAll() {

        List<Loan> loans = this.loanService.findAll();

        return this.beanMapper.mapList(loans, LoanDto.class);
    }

    /**
     * Método para recuperar un listado paginado de {@link com.ccsw.tutorial.loan.model.Loan}
     * @param dto
     * @param idGame
     * @param idClient
     * @param date
     * @return
     */
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<LoanDto> findPage(@RequestBody LoanSearchDto dto,
                                  @RequestParam(value = "idGame", required = false) Long idGame,
                                  @RequestParam(value = "idClient", required = false) Long idClient,
                                  @RequestParam(value = "date", required = false) Date date) {

        return this.beanMapper.mapPage(this.loanService.findPage(dto, idGame, idClient, date), LoanDto.class);
    }

    /**
     * Método para crear o actualizar un {@link com.ccsw.tutorial.loan.model.Loan}
     * @param id id de la entidad
     * @param data datos de la entidad
     */
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody LoanDto data) {

        this.loanService.save(id, data);
    }

    /**
     * Método para eliminar un {@link com.ccsw.tutorial.loan.model.Loan}
     * @param id PK de la entidad
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        this.loanService.delete(id);
    }
}