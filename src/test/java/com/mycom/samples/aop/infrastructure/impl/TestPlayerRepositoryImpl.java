package com.mycom.samples.aop.infrastructure.impl;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.jukito.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mycom.samples.aop.domain.PlayerRepository;
import com.mycom.samples.aop.domain.model.Instrument;
import com.mycom.samples.aop.domain.model.Instrument.Category;
import com.mycom.samples.aop.inject.guice.DomainModule;

@RunWith(JukitoRunner.class)
public class TestPlayerRepositoryImpl
{

   public static class Module extends TestModule
   {
      @Override
      protected void configureTest()
      {
         install(new DomainModule());
      }
   }

   @Inject
   private PlayerRepository repo;

   @Test
   public void testList()
   {
      assertThat(repo.list()).isNotNull().isNotEmpty();
   }

   @Test
   public void testFindByInstrument()
   {
      assertThat(repo.find(Instrument.AltoSax)).isNotNull().isNotEmpty().hasSize(1);
   }

   @Test
   public void testFindByCategory()
   {
      assertThat(repo.find(Category.Brass)).isNotNull().isNotEmpty().hasSize(2);
   }

}
