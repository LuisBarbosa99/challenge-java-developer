package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.dto.CheckCreditDTO;
import br.com.neurotech.challenge.entity.dto.ClientDTO;
import br.com.neurotech.challenge.entity.dto.ClientMinifiedDTO;
import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.enums.VehicleModel;

import java.util.List;

@Service
public interface CreditService {
	
	/**
	 * Efetua a checagem se o cliente está apto a receber crédito
	 * para um determinado modelo de veículo
	 */
	CheckCreditDTO checkCredit(String clientId, VehicleModel model);

	/**
	 * Lista os clientes disponíveis para receber crédito para modelo Hatch
	 * @return lista de clientes disponíveis.
	 */
	List<ClientMinifiedDTO> listAvailableForHatch();
}
