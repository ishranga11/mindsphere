package com.maven.mind.Controller;

import com.maven.mind.Model.Warning;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/warning")
public class WarningController {

    @RequestMapping(value = "/0", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public int saveWarning (@RequestBody Warning warning ){
        return ( new Warning()).saveWarning(warning,null);
    }

    @RequestMapping(value = "/1", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public Collection<Warning> byOpId (@RequestBody Warning id ){
        return ( new Warning()).getWarningByOpId(id,null);
    }

}
