package com.mycom.samples.aop.infrastructure.impl;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycom.samples.aop.annotations.LogPerformances;

public class LogPerformancesInterceptor implements MethodInterceptor
{
   @Override
   public Object invoke(MethodInvocation mi) throws Throwable
   {
      if (mi.getMethod().isAnnotationPresent(LogPerformances.class))
      {
         String operation = mi.getMethod().getAnnotation(LogPerformances.class).operation();
         String layer     = mi.getMethod().getAnnotation(LogPerformances.class).layer();
         long start = System.currentTimeMillis();
         Object ret = mi.proceed();
         long duration =  System.currentTimeMillis() - start;
         PERF_LOGGER.info("layer;{};operation;{};duration_ms;{}",layer,operation,duration);
         return ret;
      }
      return mi.proceed();
   }

   private static Logger PERF_LOGGER = LoggerFactory.getLogger("PERF_LOGGER");

}
