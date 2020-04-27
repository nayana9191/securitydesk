package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.model.Dresscode;
import com.codepirates.securitydesk.util.CommonFunctions;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

@Repository
public class DresscodeRepositoryImpl implements DresscodeRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommonFunctions commonFunctions;

    @Override
    public Dresscode addDresscodeViolation(Dresscode  dresscode) {
        return mongoTemplate.save(dresscode);
    }

    @Override
    public List<Dresscode> getAllDresscodeViolations() {
        return mongoTemplate.findAll(Dresscode.class);
    }

    @Override
    public List<Dresscode> getAllActiveDresscodeViolations() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is("Active"));
        return mongoTemplate.find(query, Dresscode.class);
    }
    @Override
    public UpdateResult deleteDresscodeViolation(Dresscode dresscode) {
        Query query = new Query ();
        query.addCriteria (Criteria.where ("employeeId").is (dresscode.getEmployeeId()));
        query.addCriteria (Criteria.where ("checkinTime").is (dresscode.getCheckinTime()));
        return mongoTemplate.updateFirst (query, Update.update ("status","Inactive"),Dresscode.class) ;
    }
}
