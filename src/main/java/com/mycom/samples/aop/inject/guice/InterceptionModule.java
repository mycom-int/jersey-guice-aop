package com.mycom.samples.aop.inject.guice;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.mycom.samples.aop.annotations.LogPerformances;
import com.mycom.samples.aop.infrastructure.impl.LogPerformancesInterceptor;


/**
 * Binds the method interceptor for the @LogPerformances annotation
 */
public class InterceptionModule extends AbstractModule
{

   @Override
   protected void configure()
   {

      LogPerformancesInterceptor interceptor = new LogPerformancesInterceptor();
      bindInterceptor(Matchers.any(), Matchers.annotatedWith(LogPerformances.class), interceptor);
      bind(LogPerformancesInterceptor.class).toInstance(interceptor);
      requestInjection(interceptor);
   }

}
