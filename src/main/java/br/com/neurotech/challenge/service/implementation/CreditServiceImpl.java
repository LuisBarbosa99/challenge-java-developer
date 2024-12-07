package br.com.neurotech.challenge.service.implementation;

import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.CreditService;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {
    @Override
    public boolean checkCredit(String clientId, VehicleModel model) {
        return true;
    }
}
