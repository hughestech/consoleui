/**
 * 
 */
package io.hughestech.consoleui;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@ReadEventAnnotation
@Interceptor
/**
 * @author anton
 *
 */
public class EventReaderInterceptor {

	/**
	 * 
	 */
	public EventReaderInterceptor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ic
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object aroundInvoke(InvocationContext invocationContext) throws Exception {
		System.out.println("Entering method: "
                + invocationContext.getMethod().getName() + " in class "
                + invocationContext.getMethod().getDeclaringClass().getName());

        return invocationContext.proceed();
	}

}
