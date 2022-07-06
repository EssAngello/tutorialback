package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * @author ccsw
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService{

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    GameService gameService;

    @Autowired
    ClientService clientService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Loan> findAll() {

        return (List<Loan>) this.loanRepository.findAll();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Loan get(Long id) {

        return this.loanRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Loan> findPage(LoanSearchDto dto, Long idGame, Long idClient, Date date) {
        return this.loanRepository.findAll(dto.getPageable(), idGame, idClient, date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, LoanDto data) {

        if(!loanIsPossible(data))
            return;

        Loan loan = null;
        if (id != null)
            loan = this.get(id);
        else
            loan = new Loan();

        BeanUtils.copyProperties(data, loan, "id", "game", "client");

        loan.setClient(clientService.get(data.getClient().getId()));

        loan.setGame(gameService.get(data.getGame().getId()));

        this.loanRepository.save(loan);
    }

    public boolean loanIsPossible(LoanDto data){
        List<Loan> loans  = this.findAll();

        for(Loan loan2:loans){

            if( data.getId()!=loan2.getId() && ((data.getGame().getId()==loan2.getGame().getId()) ||  (data.getClient().getId()==loan2.getClient().getId())))
            {
                if( (data.getStartDate().after(loan2.getStartDate()) &&  data.getStartDate().before(loan2.getEndDate()) )  ||     (data.getEndDate().after(loan2.getStartDate()) &&  data.getEndDate().before(loan2.getEndDate()) )) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {

        this.loanRepository.deleteById(id);

    }

}
