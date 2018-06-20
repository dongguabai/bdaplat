package com.zj.bda.web.controller.base;

import com.zj.bda.common.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dongguabai on 2018-06-20 10:41
 */
@Controller
public class NotFoundController {

    @RequestMapping
    public void notFound() {
        throw new NotFoundException();
    }
}
