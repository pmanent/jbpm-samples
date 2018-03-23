/**
 * 
 */
package bpm.samples;


import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

/**
 * @author peremanent
 *
 */
public class ServiceWorkItemHandler implements WorkItemHandler {
	
	public ServiceWorkItemHandler() {
		System.out.println("Constructor");

	}
	/* (non-Javadoc)
	 * @see org.kie.api.runtime.process.WorkItemHandler#executeWorkItem(org.kie.api.runtime.process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		System.out.println("Entrem i fem la crida a al Servei");
		// extract parameters
	    String from = (String) workItem.getParameter("From");
	    String to = (String) workItem.getParameter("To");
	    String message = (String) workItem.getParameter("Message");
	    String priority = (String) workItem.getParameter("Priority");
	    System.out.println("from: "+from);
	    System.out.println("to: "+to);
	    System.out.println("message: "+message);
	    System.out.println("priority: "+priority);
	    //<bpmn2:task id="Task_3" name="WorkItem" tns:taskName="ServiceWorkItemHandler">

	    // notify manager that work item has been completed
	    manager.completeWorkItem(workItem.getId(), null); 
	}

	/* (non-Javadoc)
	 * @see org.kie.api.runtime.process.WorkItemHandler#abortWorkItem(org.kie.api.runtime.process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub

	}

}
