package za.co.hqtech.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.hqtech.entity.Message;
import za.co.hqtech.repository.NotificationRepository;

@RestController
@RequestMapping("/api")
public class NotificationController {

	@Autowired
	private NotificationRepository notificationRepository;

	@CrossOrigin
	@PostMapping("/messaging")
	public Message createNotification(@Valid @RequestBody Message message) {
		return notificationRepository.save(message);
	}

	@CrossOrigin
	@GetMapping("/messaging/{sentTo}")
	public List<Message> getNotificationByUser(@PathVariable(value = "sentTo") String sentTo) {
		return notificationRepository.findBySentTo(sentTo);
		
	}

	@CrossOrigin
	@GetMapping("/messaging/{sentTo}/{status}")
	public List<Message> getNotificationByUserAndRead(@PathVariable(value = "sentTo") String sentTo,
			@PathVariable(value = "status") Boolean isRead) {
		return notificationRepository.findBySentToIsRead(isRead, sentTo);
	}

	@CrossOrigin
	@GetMapping("/messaging/status/{isRead}/id/{id}")
	public Integer changeMessageStatus(@PathVariable(value = "id") Integer id,
			@PathVariable(value = "isRead") Boolean isRead) {
		return notificationRepository.changeMessageStatus(id, isRead);
	}

	

}

