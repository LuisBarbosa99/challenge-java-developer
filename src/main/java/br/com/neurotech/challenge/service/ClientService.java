package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.dto.ClientDTO;
import br.com.neurotech.challenge.entity.model.Client;
import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.form.NeurotechClient;

import java.util.List;

@Service
public interface ClientService {
	
	/**
	 * Salva um novo cliente
	 * 
	 * @return ID do cliente recém-salvo
	 */
	String save(NeurotechClient client);
	
	/**
	 * Recupera um cliente baseado no seu ID
	 */
	NeurotechClient get(String id);

	/**
	 * Lista todos os clientes
	 */
	List<ClientDTO> findAll();
}
