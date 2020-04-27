package com.codepirates.securitydesk.controller;

import com.codepirates.securitydesk.model.Dresscode;
import com.codepirates.securitydesk.repository.DresscodeRepository;
import com.codepirates.securitydesk.util.CommonFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DresscodeController {

    @Autowired
    private DresscodeRepository dresscodeRepository;

    @Autowired
    private CommonFunctions commonFunctions;

    @RequestMapping(value = "/addDresscode", method = RequestMethod.POST)
    public List<Dresscode> addDresscodeViolations(@RequestBody Dresscode dresscode) {
        dresscode.setCheckinTime(commonFunctions.currentDateAndTime());
        dresscode.setStatus("Active");
        dresscodeRepository.addDresscodeViolation(dresscode);
        return dresscodeRepository.getAllActiveDresscodeViolations();
    }


    @RequestMapping(value = "/getDresscode", method = RequestMethod.GET)
    public List<Dresscode> getAlldresscodeViolations() {

        return dresscodeRepository.getAllActiveDresscodeViolations();
    }

    @RequestMapping(value = "/deleteDresscode", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dresscode> deleteDresscodeViolations(@RequestBody Dresscode dresscode) {
        dresscodeRepository.deleteDresscodeViolation(dresscode);
        return dresscodeRepository.getAllActiveDresscodeViolations();
    }
}
