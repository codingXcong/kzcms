package cn.zgc.cms.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zgc.cms.dto.TopicDto;
import cn.zgc.cms.model.Topic;
import cn.zgc.cms.service.IChannelService;
import cn.zgc.cms.service.ITopicService;

/** 
 * @author gczhang  
 * 
 */
@Controller
@RequestMapping("/admin/topic")
public class TopicController {
	@Resource
	private ITopicService topicService;
	@Resource
	private IChannelService channelService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		Topic t = new Topic();
		t.setPublishDate(new Date());
		TopicDto td = new TopicDto(t); 
		model.addAttribute("topicDto",td);
		return "topic/add";
	}
}
