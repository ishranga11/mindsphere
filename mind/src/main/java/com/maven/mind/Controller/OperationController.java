package com.maven.mind.Controller;

import com.maven.mind.Model.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/op")
public class OperationController {

    @RequestMapping(value = "/0", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public int saveOperations (@RequestBody Operation op ){
        return ( new Operation()).saveOp(op,null);
    }

    @RequestMapping(value = "/1", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public Collection<Operation> getDoneOperations (@RequestBody int hospId ){
        return ( new Operation()).getDoneOpsByHospital(hospId,null);
    }

    @RequestMapping(value = "/2", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public Collection<Operation> getLiveOperations (@RequestBody int hospId ){
        return ( new Operation()).getLiveOpsByHospital(hospId,null);
    }

    @RequestMapping(value = "/3", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public Collection<Operation> getOperations (@RequestBody int hospId ){
        return ( new Operation()).getOpsByHospital(hospId,null);
    }

    @RequestMapping(value = "/4", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public int opDone (@RequestBody Operation id ){
        return ( new Operation()).opDone(id,null);
    }

}
