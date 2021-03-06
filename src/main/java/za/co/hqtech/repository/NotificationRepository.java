package za.co.hqtech.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import za.co.hqtech.entity.Message;


@Repository
public interface NotificationRepository extends JpaRepository<Message, Long> {

	List<Message> findBySentTo(@Param("sentTo") String sentTo);

	@Query("SELECT m FROM Message m WHERE m.isRead = :isRead AND m.sentTo = :sentTo")
	List<Message> findBySentToIsRead(@Param("isRead") Boolean isRead, @Param("sentTo") String sentTo);

	@Transactional
	@Modifying
	@Query("UPDATE Message m SET m.isRead = :isRead WHERE m.id = :id")
	Integer changeMessageStatus(@Param("id") Integer id, @Param("isRead") Boolean isRead);
}

