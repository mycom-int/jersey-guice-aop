package com.mycom.samples.aop.inject;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycom.samples.aop.application.MusicianFinderApp;
import com.mycom.samples.aop.application.impl.MusicianFinderAppImpl;
import com.mycom.samples.aop.domain.PlayerRepository;
import com.mycom.samples.aop.infrastructure.impl.LogPerformancesInterceptor;
import com.mycom.samples.aop.infrastructure.impl.PlayerRepositoryImpl;
import com.mycom.samples.aop.inject.guice.DomainModule;
import com.mycom.samples.aop.inject.guice.InterceptionModule;

public class TestModules
{

   @Test
   public void testGuiceInterceptionModule()
   {
      Injector injector = Guice.createInjector(new InterceptionModule());
      assertThat(injector.getInstance(LogPerformancesInterceptor.class))
         .isNotNull()
         .isInstanceOf(LogPerformancesInterceptor.class);

      assertThat(injector.getProvider(LogPerformancesInterceptor.class))
         .isNotNull();
   }

   @Test
   public void testGuiceApplicationModule()
   {
      Injector injector = Guice.createInjector(new DomainModule());
      assertThat(injector.getInstance(PlayerRepository.class)).isNotNull().isInstanceOf(PlayerRepositoryImpl.class);
      assertThat(injector.getInstance(MusicianFinderApp.class)).isNotNull().isInstanceOf(MusicianFinderAppImpl.class);
   }
}
