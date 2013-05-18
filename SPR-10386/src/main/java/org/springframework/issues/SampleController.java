package org.springframework.issues;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @RequestMapping("/simple")
    public void sample() {
    }

    @RequestMapping({"/site/*/{id}.p", "/site/*/{id}.p;jsessionid=*"})
    public void problem(@PathVariable String id, @PathVariable String selectedTab) {
    }
}
