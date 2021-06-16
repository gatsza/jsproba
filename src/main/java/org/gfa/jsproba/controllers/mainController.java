package org.gfa.jsproba.controllers;

import org.gfa.jsproba.models.Message;
import org.gfa.jsproba.repositories.MessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

  MessageRepository repository;

  public mainController(MessageRepository repository){
    this.repository = repository;
  }

  @GetMapping("/")
  public String showIndex(Model model){
    model.addAttribute("datas", repository.findAll());
    return "index";
  }

  @MessageMapping("/receive")
  @SendTo("/topic/send")
  public Message greeting(Message message)  {
    repository.save(message);

    return message;
  }
}
