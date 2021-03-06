package org.pseudoscript.program;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pseudoscript.data.DataSource;
import org.pseudoscript.data.exception.DataNotFoundException;
import org.pseudoscript.data.exception.IllegalKeyException;
import org.pseudoscript.program.exception.DataSourceNotFoundException;
import org.pseudoscript.program.exception.ExecutorNotFoundException;
import org.pseudoscript.program.exception.OperationNotFoundException;
import org.pseudoscript.script.ArgumentInfo;
import org.pseudoscript.script.OperationInfo;
import org.pseudoscript.script.ReferredArgumentInfo;
import org.pseudoscript.script.ReferredDataSource;
import org.pseudoscript.script.Script;
import org.pseudoscript.script.SimpleArgumentInfo;

public class Interpreter {

	private final Map<String, DataSource> dataSources;
	private final Map<String, ExecutorInfo> executors;

	public Interpreter(Map<String, DataSource> dataSources, Map<String, ExecutorInfo> executors) {
		this.dataSources = dataSources;
		this.executors = executors;
	}

	public Program interpret(Script script) 
			throws ExecutorNotFoundException, OperationNotFoundException, DataSourceNotFoundException, 
			DataNotFoundException, IllegalKeyException {
		Program program = new ProgramImpl();

		for (OperationInfo operationInfo : script.getOperations()) {
			String executorName = operationInfo.getExecutor();
			ExecutorInfo executorInfo = executors.get(executorName);
			if (executorInfo == null) {
				throw new ExecutorNotFoundException(executorName, 
						String.format("Executor \"%s\" doesn't exist.", executorName));
			}

			String operationName = operationInfo.getName();
			Method method = executorInfo.getMethods().get(operationName);
			if (method == null) {
				throw new OperationNotFoundException(operationName,
						String.format("Operation \"%s\" doesn't exist.", operationName));
			}
			
			List<Object> argumentList = new ArrayList<>();
			for (ArgumentInfo argumentInfo : operationInfo.getArguments()) {
				Object argument = null;
				
				if (argumentInfo instanceof ReferredArgumentInfo) {
					ReferredArgumentInfo referredArgumentInfo = (ReferredArgumentInfo) argumentInfo;
					ReferredDataSource referredDataSource = referredArgumentInfo.getDataSource();
					
					// Get real data source ID and full key.
					String realDataSourceId = null;
					String fullKey = null;
					if (referredDataSource.getBase() != null && referredDataSource.getKey() != null) {
						realDataSourceId = referredDataSource.getBase();
						fullKey = referredDataSource.getKey() + "." + referredArgumentInfo.getKey();
					} else {
						realDataSourceId = referredDataSource.getId();
						fullKey = referredArgumentInfo.getKey();
					}
					
					// Retrieve data from real data source.
					DataSource dataSource = dataSources.get(realDataSourceId);
					if (dataSource == null) {
						throw new DataSourceNotFoundException(realDataSourceId, 
								String.format("Data source \"%s\" doesn't exist.", realDataSourceId));
					}
					argument = dataSource.get(fullKey);
				} else {
					SimpleArgumentInfo simpleArgumentInfo = (SimpleArgumentInfo) argumentInfo;
					argument = simpleArgumentInfo.getValue();
				}
				
				argumentList.add(argument);
			}
			Object[] arguments = new Object[argumentList.size()];
			argumentList.toArray(arguments);
			
			Operation operation = new OperationImpl();
			operation.setExecutor(executorInfo);
			operation.setName(operationName);
			operation.setArguments(arguments);
			
			program.getOperations().add(operation);
		}

		return program;
	}

}
