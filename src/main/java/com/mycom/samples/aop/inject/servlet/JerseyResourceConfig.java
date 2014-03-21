package com.mycom.samples.aop.inject.servlet;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.jvnet.hk2.guice.bridge.api.HK2IntoGuiceBridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.mycom.samples.aop.inject.hk2.RestInterceptorBinder;

public class JerseyResourceConfig extends ResourceConfig
{
   private static Logger logger = LoggerFactory.getLogger(JerseyResourceConfig.class);


   @Inject
   public JerseyResourceConfig(ServiceLocator serviceLocator)
   {
      logger.info("Registering injectables ....");
      register(new RestInterceptorBinder());
      registerProviders();
      createBiDirectionalGuiceBridge(serviceLocator, GuiceServletConfig.getAllModulesAsArray());
   }

   private void registerProviders()
   {
      ObjectMapper mapper = new ObjectMapper();
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
      provider.setMapper(mapper);
      register(provider);
   }

   public Injector createBiDirectionalGuiceBridge(ServiceLocator serviceLocator, Module... applicationModules)
   {
      Module[] allModules = new Module[applicationModules.length + 1];

      allModules[0] = new HK2IntoGuiceBridge(serviceLocator);
      for (int lcv = 0; lcv < applicationModules.length; lcv++)
      {
         allModules[lcv + 1] = applicationModules[lcv];
      }

      Injector injector = GuiceServletConfig.getInjectorInstance();
      GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
      GuiceIntoHK2Bridge g2h = serviceLocator.getService(GuiceIntoHK2Bridge.class);
      g2h.bridgeGuiceInjector(injector);

      return injector;
   }

}
