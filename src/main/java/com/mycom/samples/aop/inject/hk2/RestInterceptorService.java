package com.mycom.samples.aop.inject.hk2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import org.aopalliance.intercept.ConstructorInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.InterceptionService;
import org.glassfish.hk2.utilities.BuilderHelper;

import com.google.common.collect.Lists;
import com.mycom.samples.aop.annotations.LogPerformances;
import com.mycom.samples.aop.infrastructure.impl.LogPerformancesInterceptor;


/**
 * HK2 Method Interception service
 */

public class RestInterceptorService implements InterceptionService
{
   @Inject
   private Provider<LogPerformancesInterceptor> interceptorProvider;

   @Override
   public List<ConstructorInterceptor> getConstructorInterceptors(Constructor<?> arg0)
   {
      return Lists.newArrayList();
   }

   @Override
   public Filter getDescriptorFilter()
   {
      return BuilderHelper.allFilter();
   }

   @Override
   public List<MethodInterceptor> getMethodInterceptors(Method method)
   {
      List<MethodInterceptor> ret = Lists.newArrayList();
      if (method.isAnnotationPresent(LogPerformances.class))
         ret.add(interceptorProvider.get());
      return ret;
   }

}
