/**
 * 
 */
package bpm.samples;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;

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
        
        ksession.getWorkItemManager().registerWorkItemHandler("ServiceWorkItemHandler",
        		handler);
        
        // Start a process
	    ProcessInstance processInstance = ksession.startProcess("jbpm.Sample");
	    
	         		
        
	}
}
