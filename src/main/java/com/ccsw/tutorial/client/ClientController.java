package com.ccsw.tutorial.client;

import java.util.List;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.client.model.ClientDto;
import com.devonfw.module.beanmapping.common.api.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ccsw
 */
@RequestMapping(value = "/client")
@RestController
@CrossOrigin(origins = "*")

public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    BeanMapper beanMapper;

    /**
     * Método para recuperar todos los {@link com.ccsw.tutorial.client.model.Client}
     * @return
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ClientDto> findAll() {

        return this.beanMapper.mapList(this.clientService.findAll(), ClientDto.class);
    }

    /**
     * Método para crear o actualizar un {@link com.ccsw.tutorial.client.model.Client}
     * @param dto
     * @return
     */
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody ClientDto dto) {

        this.clientService.save(id, dto);
    }

    /**
     * Método para borrar un {@link com.ccsw.tutorial.client.model.Client}
     * @param id
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        this.clientService.delete(id);

    }

    @RequestMapping(path = "/{name}" , method = RequestMethod.GET)
    public boolean checkName(@PathVariable("name") String name) {
        return this.clientService.checkName(name);
    }

}
