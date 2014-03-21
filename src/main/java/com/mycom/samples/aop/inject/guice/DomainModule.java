package com.mycom.samples.aop.inject.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.mycom.samples.aop.application.MusicianFinderApp;
import com.mycom.samples.aop.application.impl.MusicianFinderAppImpl;
import com.mycom.samples.aop.domain.PlayerRepository;
import com.mycom.samples.aop.infrastructure.impl.PlayerRepositoryImpl;

/**
 * Bindings for our tiny domain
 */
public class DomainModule extends AbstractModule
{

   @Override
   protected void configure()
   {
      bind(PlayerRepository.class).to(PlayerRepositoryImpl.class).in(Singleton.class);
      bind(MusicianFinderApp.class).to(MusicianFinderAppImpl.class).in(Singleton.class);
   }

}
