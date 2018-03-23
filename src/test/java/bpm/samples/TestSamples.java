/**
 * 
 */
package bpm.samples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.bpmn2.handler.ServiceTaskHandler;
import org.jbpm.process.instance.impl.humantask.HumanTaskHandler;
import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;

/**
 * @author peremanent
 *
 */
public class TestSamples extends JbpmJUnitBaseTestCase{
	
	@Test
	public void test() {
		// create runtime manager with single process - hello.bpmn
		createRuntimeManager("bpm/diagram/Sample.bpmn2");
		
		// take RuntimeManager to work with process engine
		RuntimeEngine runtimeEngine = getRuntimeEngine();
        
        
        // get access to KieSession instance
        KieSession ksession = runtimeEngine.getKieSession();
        
        
        ServiceWorkItemHandler handler = new ServiceWorkItemHandler();
        HumanTaskHandler humanHandler= new HumanTaskHandler();
        
        ksession.getWorkItemManager().registerWorkItemHandler("ServiceWorkItemHandler",
        		handler);
        ksession.getWorkItemManager().registerWorkItemHandler("Human Task",
        		humanHandler);
        ksession.getWorkItemManager().registerWorkItemHandler("Service Task",        new ServiceTaskHandler());
        
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("From","From potaMadre");
        parameters.put("To","To potaMadre");
        
        // Start a process
	    ProcessInstance processInstance = ksession.startProcess("jbpm.Sample");
	    
	    try {
			// Do Task Operations
	    	//TODO FIX get Task Service
			TaskService taskService = runtimeEngine.getTaskService();
			List<TaskSummary> tasksAssignedAsPotentialOwner = taskService.getTasksAssignedAsPotentialOwner("Mary", "en-UK");
			TaskSummary taskSummary = tasksAssignedAsPotentialOwner.get(0);
			// Claim Task
			taskService.claim(taskSummary.getId(), "Mary");
			// Start Task
			taskService.start(taskSummary.getId(), "Mary");
			taskService.complete(taskSummary.getId(), "Mary", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    // check whether the process instance has completed successfully
        //assertProcessInstanceCompleted(processInstance.getId());

        // check what nodes have been triggered
        assertNodeTriggered(processInstance.getId(), "StartProcess", "WorkItem");
	         		
        
	}
}
