package com.mycom.samples.aop.inject.hk2;

import javax.inject.Singleton;

import org.glassfish.hk2.api.InterceptionService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Interception service binding for HK2
 */
public class RestInterceptorBinder extends AbstractBinder
{
   @Override
   protected void configure()
   {
      bind(RestInterceptorService.class).to(InterceptionService.class).in(Singleton.class);
   }
}

