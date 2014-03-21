package com.mycom.samples.aop.application.impl;

import java.util.List;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import com.mycom.samples.aop.application.MusicianFinderApp;
import com.mycom.samples.aop.domain.PlayerRepository;
import com.mycom.samples.aop.domain.model.Instrument.Category;
import com.mycom.samples.aop.domain.model.Player;

public class MusicianFinderAppImpl implements MusicianFinderApp
{
   @Inject
   private PlayerRepository playerRepo;

   @Override
   public List<Player> findMusician(Category category)
   {
      return Lists.newArrayList(playerRepo.find(category));
   }

   @Override
   public List<Player> listMusicians()
   {
      return Lists.newArrayList(playerRepo.list());
   }

}
