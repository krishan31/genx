package com.example.openAMactiviti.MyService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.openAMactiviti.OpenamAPI;
import com.example.openAMactiviti.Person;
import com.example.openAMactiviti.PersonRepository;
import com.example.openAMactiviti.Result;
import com.example.openAMactiviti.Root;

@Service
@Transactional
public class MyService {

	public String username, cn, sn;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private PersonRepository personRepository;

	public void startProcess(String assignee) {
		List<Person> person = personRepository.findByUsername(assignee);
		for(Person ps: person ) {
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("person", ps);
			runtimeService.startProcessInstanceByKey("process_one", variables);
			System.out.println("Start-processuuuuuuuuuuuuu");
		}		
	}

	@Transactional
	public List<Task> getTasks(String assignee) {
		System.out.println("gettask  ===================  ");
		System.out.println("assignee       +++++++++++++++  " + assignee);
		List<Task> a = taskService.createTaskQuery().taskAssignee(assignee.toString()).list(); // break
		System.out.println("aaaaaaa task String    ++++++++++++++++  " + a);
		return taskService.createTaskQuery().taskAssignee(assignee).list();
	}
//
//	public void createDemoUsers() {
//		if (personRepository.findAll().size() == 0) {
//
//			personRepository.save(new Person(username, cn, sn, new Date()));
//			personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
//
////          personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
//
//		} else if (personRepository.findByUsername(username) == personRepository.findAll()) {
//			personRepository.save(new Person("mblaze", "mock", "blaze", new Date()));
//			personRepository.save(new Person("blaze", "ock", "laze", new Date()));
//			personRepository.save(new Person("la", "k", "ze", new Date()));
//		} else {
//
////        	  personRepository.save(new Person ("user", "user", "user", new Date()));
//		}
//
//	}

	public void Openauthentication() {
		HttpHeaders requestHeaders = new HttpHeaders();
		String Username = "amAdmin";
		String Password = "qwerty12345";
		requestHeaders.add("X-OpenAM-Username", Username);
		requestHeaders.add("X-OpenAM-Password", Password);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
		System.out.println("requestEntity ==============================  " + requestEntity);
		// logger.info("response entity "+requestEntity.toString());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(OpenamAPI.TOKEN, HttpMethod.POST, requestEntity,
				String.class);
		JSONObject object = new JSONObject(result.getBody());
		System.out.println("json object response" + object);
		String tokenId = (String) object.get("tokenId");
		// TokenValidation(tokenId);
		Admindetail(tokenId);
	}

	public void Admindetail(String tokenId) {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("iPlanetDirectoryPro", tokenId);

		HttpEntity<Root> requestEntity = new HttpEntity<>(requestHeaders);
		System.out.println("requestEntity=============" + requestEntity);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Root> result = restTemplate.exchange(OpenamAPI.USERDATA, HttpMethod.GET, requestEntity,
				Root.class);

//			
		System.out.println("checking :" + result.getBody());

		System.out.println("User Details== :" + result.getBody().getResult());
		for (Result ls : result.getBody().getResult()) {
			System.out.println("List of users" + ls.getUsername());
			System.out.println("List of SN" + ls.getSn());
			System.out.println("List of CN" + ls.getCn());
			createDemoUsers(ls.getUsername(), ls.getSn(), ls.getCn());
		}

	}

	private void createDemoUsers(String username2, List<String> sn2, List<String> cn2) {
		List<Person> ps = personRepository.findByUsername(username2);
		try {
			System.out.println(ps.size());

			if (ps.size()==0) {
				for (int i = 0; i < sn2.size(); i++) {
					System.out.println("List get" + sn2.get(i));
					System.out.println("List get" + cn2.get(i));
					{
						personRepository.save(new Person(username2, sn2.get(i), cn2.get(i), new Date()));
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
