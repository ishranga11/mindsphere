package com.maven.mind.Controller;

import com.maven.mind.Model.Info;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/info")
public class InfoController {

    @RequestMapping(value = "/1", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public int getId (@RequestBody Info info ){
        return ( new Info()).getId(info,null);
    }

    @RequestMapping(value = "/2", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public Info getId (@RequestBody int id ){
        return ( new Info()).getInfo(id,null);
    }

    @RequestMapping(value = "/0", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
    public int saveInfo (@RequestBody Info info ){
        return ( new Info()).saveInfo(info,null);
    }
}
