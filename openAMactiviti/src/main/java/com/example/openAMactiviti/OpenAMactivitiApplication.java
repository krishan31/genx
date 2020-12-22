package com.example.openAMactiviti;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.openAMactiviti.MyService.MyService;


@Configuration
@ComponentScan
//@EnableAutoConfiguration
//
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
public class OpenAMactivitiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenAMactivitiApplication.class, args);
		
	}
	@Bean
    public CommandLineRunner init(final MyService myService,
    		final TaskService taskService,
    		final RepositoryService repositoryService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
//                System.out.println("Number of process definitions : "
//                	+ repositoryService.createProcessDefinitionQuery().count());
//                System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
//                runtimeService.startProcessInstanceByKey("process_one");
//                System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
            	myService.Openauthentication();
            
            }
        };

        
    }
	@Autowired
    private MyService myService;
	
	 @RequestMapping(value="/process", method= RequestMethod.POST)
	    public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation) {
		 System.out.println("helloooo================");
		 myService.startProcess(startProcessRepresentation.getAssignee());
	       
	    }
	
	 
	 @Autowired
	 private RepositoryService repositoryService;
	 @RequestMapping(value="/process-definition")
	 public HashMap<String,Object> definiton() {
		 HashMap<String,Object> qwe= new HashMap<String,Object>();
  
		 qwe.put("Id", repositoryService.getProcessDefinition("process_one:2:2503").getId());
		 qwe.put("Key",  repositoryService.getProcessDefinition("process_one:2:2503").getKey());
		 qwe.put ("Name",  repositoryService.getProcessDefinition("process_one:2:2503").getName());
		 qwe.put ("Category", repositoryService.getProcessDefinition("process_one:2:2503").getCategory());
		 qwe.put ("Resource Name", repositoryService.getProcessDefinition("process_one:2:2503").getResourceName());
		 
		 return qwe;
	    }
		
	 
	 
	 @Autowired
		private TaskService taskService;
	 
	 @RequestMapping("/complete-task")
	    public String completeTask(@RequestParam(name="taskId") String taskId) {
	       taskService.complete(taskId);
	        return "Completed Task: " + taskId;
	    }
	 
	 
	 @RequestMapping(value="/tasks", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	    public List<TaskRepresentation> getTasks(@RequestParam(name="assignee") String assignee) {
		 System.out.println("task =====================  ");
		 System.out.println("assigneee ================  "+assignee);
	        List<Task> tasks = myService.getTasks(assignee.toString());  //break
	        System.out.println("before for loop ============= "+tasks);
	        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
	        System.out.println("task re    =======================================");
	        
	        for (Task task : tasks) {
	        	System.out.println("inside for loop =============");
	        	System.out.println("task name ============  "+task.getName()+" task id  ====================  "+task.getId());
	            dtos.add(new TaskRepresentation(task.getId(), task.getName(), task.getAssignee(), task.getExecutionId(), 
	            								task.getOwner(), task.getProcessInstanceId(), task.getTaskDefinitionKey(), task.getProcessDefinitionId()));
	        }
	        return dtos;
	    }
	 
	 

	 static class TaskRepresentation {

	        private String id;
	        private String name;
	        private String assignee;
	        private String executionid;
	        private String owner;
	        private String procinstid;
	        private String taskdefkey;
	        private String procdefid;

	        public TaskRepresentation(String id, String name, String assignee, 
	        	   String executionid, String owner, String procinstid, String taskdefkey, String procdefid) {
	            this.id = id;
	            this.name = name;
	            this.assignee = assignee;
	            this.executionid = executionid;
	            this.owner = owner;
	            this.procinstid = procinstid;
	            this.taskdefkey = taskdefkey;
	            this.procdefid = procdefid;
	        }

	         public String getId() {
	            return id;
	        }
	        public void setId(String id) {
	            this.id = id;
	        }
	        public String getName() {
	            return name;
	        }
	        public void setName(String name) {
	            this.name = name;
	        }
	        public String getAssignee() {
	            return assignee;
	        }
	        public void setAssignee(String assignee) {
	            this.assignee = assignee;
	        }
	        public String getExecutionId() {
	        	return executionid;
	        }
	        public void setExecution_Id(String executionid) {
	        	this.executionid = executionid;
	        }
	        public String getOwner() {
	        	return owner;
	        }
	        public void setOwner(String owner) {
	        	this.owner = owner;
	        }
	        public String getProcinstId() {
	        	return procinstid;
	        }
	        public void setProcinstId(String procinstid) {
	        	this.procinstid = procinstid;
	        }
	        public String getTaskdefkey() {
	        	return taskdefkey;
	        }
	        public void setTaskdefkey(String taskdefkey) {
	        	this.taskdefkey = taskdefkey;
	        }
	        public String getProcdefId() {
	        	return procdefid;
	        }
	        public void setProcdefId(String procdefid) {
	        	this.procdefid = procdefid;
	        }
	   
	        }
	        
	 static class ProcessDef {
		    private String iid;
	        private String name;
	        
	        public ProcessDef(String iid, String name) {
	        	this.iid = iid;
	        	this.name = name;
	        }
	        public String getId() {
	            return iid;
	        }
	        public void setId(String iid) {
	            this.iid = iid;
	        }
	        public String getName() {
	            return name;
	        }
	        public void setName(String name) {
	            this.name = name;
	        }
	 }
	 
	 
	 
	 
	 
	 static class StartProcessRepresentation {

	        private String assignee;

	        public String getAssignee() {
	            return assignee;
	        }

	        public void setAssignee(String assignee) {
	            this.assignee = assignee;
	        }
	    }
}

