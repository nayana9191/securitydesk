package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.model.Dresscode;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface DresscodeRepository {

    Dresscode addDresscodeViolation(Dresscode dresscode);

    List<Dresscode> getAllDresscodeViolations();

    List<Dresscode> getAllActiveDresscodeViolations();

    UpdateResult deleteDresscodeViolation(Dresscode dresscode);
}
