package com.mycom.samples.aop.application;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.jukito.TestModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mycom.samples.aop.domain.model.Instrument.Category;
import com.mycom.samples.aop.inject.guice.DomainModule;

@RunWith(JukitoRunner.class)
public class TestMusicianFinderApp
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
   MusicianFinderApp app;

   @Test
   public void testList()
   {
      assertThat(app.listMusicians()).isNotNull().isNotEmpty().hasSize(2);
   }

   @Test
   public void testFind()
   {
      assertThat(app.findMusician(Category.Brass)).isNotNull().isNotEmpty().hasSize(2);
      assertThat(app.findMusician(Category.Chords)).isNotNull().isEmpty();
   }

}
