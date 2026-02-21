package OnlineBookResellingSystem.OBRS_BackEnd.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DummyController
{
    @GetMapping("/")
    public String forward()
    {
        return "redirect:/api/greet";
    }
}
