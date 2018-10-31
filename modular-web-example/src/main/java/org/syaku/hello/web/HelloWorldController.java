package org.syaku.hello.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 31/10/2018
 */
@Controller
@RequestMapping("/example/hello")
public class HelloWorldController {

  @GetMapping
  @ResponseBody
  public String getHelloWorld() {
    return "Hello World";
  }
}
