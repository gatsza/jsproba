package org.gfa.jsproba.repositories;

import java.util.List;
import org.gfa.jsproba.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
  @Override
  List<Message> findAll();
}
