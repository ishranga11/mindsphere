package com.maven.mind.Controller;

import com.maven.mind.Model.Status;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/status")
public class StatusController {

    @RequestMapping(value = "/1", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public Collection<Status> getStatus (@RequestBody Status status ){
        return ( new Status()).getStatusByOpId(status,null);
    }

    @RequestMapping(value = "/0", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public int saveStatus (@RequestBody Status status ){
        return ( new Status()).saveStatus(status,null);
    }

}
