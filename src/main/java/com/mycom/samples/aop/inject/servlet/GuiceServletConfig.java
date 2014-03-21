package com.mycom.samples.aop.inject.servlet;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import jersey.repackaged.com.google.common.collect.Lists;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.mycom.samples.aop.inject.guice.DomainModule;
import com.mycom.samples.aop.inject.guice.InterceptionModule;

public class GuiceServletConfig extends GuiceServletContextListener
{
   private static Injector INJECTOR;
   private static List<Module>  MODULES = Lists.newArrayList();

   public static Injector getInjectorInstance()
   {
      return INJECTOR;
   }

   public static List<Module> getAllModules()
   {
      return MODULES;
   }

   public static Module[] getAllModulesAsArray()
   {
      return (Module[]) MODULES.toArray(new Module[MODULES.size()]);
   }

   public static void createInjectorInstance(ServletContext srvCtx)
   {
      MODULES.add(new InterceptionModule());
      MODULES.add(new DomainModule());
      INJECTOR = Guice.createInjector(MODULES);
   }

   public static void finalizeInjectorInstance()
   {
      INJECTOR = null;
   }

   @Override
   protected Injector getInjector()
   {
      return getInjectorInstance();
   }

   @Override
   public void contextInitialized(ServletContextEvent servletContextEvent)
   {
      createInjectorInstance(servletContextEvent.getServletContext());
      super.contextInitialized(servletContextEvent);
   }

   @Override
   public void contextDestroyed(ServletContextEvent servletContextEvent)
   {
      try
      {
         ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
         if (loggerFactory instanceof LoggerContext)
         {
            LoggerContext context = (LoggerContext) loggerFactory;
            context.stop();
         }
         super.contextDestroyed(servletContextEvent);
      }
      finally
      {
         finalizeInjectorInstance(); // force GC.
      }
   }

}
