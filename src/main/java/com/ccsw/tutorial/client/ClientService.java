package com.ccsw.tutorial.client;

import java.util.List;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

public interface ClientService {

    /**
     * Recupera un {@link com.ccsw.tutorial.client.model.Client} a partir de su ID
     * @param id
     * @return
     */
    Client get(Long id);

    /**
     * Método para recuperar todos los {@link com.ccsw.tutorial.client.model.Client}
     * @return
     */
    List<Client> findAll();

    /**
     * Método para crear o actualizar un {@link com.ccsw.tutorial.client.model.Client}
     * @param dto
     * @return
     */
    void save(Long id, ClientDto dto);

    /**
     * Método para borrar un {@link com.ccsw.tutorial.client.model.Client}
     * @param id
     */
    void delete(Long id);

    /**
     * Método para comprobar si ya existe un nombre repetido de un {@link com.ccsw.tutorial.client.model.Client}
     * @param name
     * @return
     */
    boolean checkName(String name);
}
