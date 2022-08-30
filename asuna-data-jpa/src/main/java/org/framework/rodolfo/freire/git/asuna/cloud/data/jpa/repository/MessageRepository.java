package org.framework.rodolfo.freire.git.asuna.cloud.data.jpa.repository;

import org.framework.rodolfo.freire.git.asuna.cloud.data.jpa.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
