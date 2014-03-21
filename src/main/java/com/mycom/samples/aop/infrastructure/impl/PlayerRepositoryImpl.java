package com.mycom.samples.aop.infrastructure.impl;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jersey.repackaged.com.google.common.collect.Maps;
import jersey.repackaged.com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mycom.samples.aop.annotations.LogPerformances;
import com.mycom.samples.aop.domain.PlayerRepository;
import com.mycom.samples.aop.domain.model.Instrument;
import com.mycom.samples.aop.domain.model.Instrument.Category;
import com.mycom.samples.aop.domain.model.Player;

public class PlayerRepositoryImpl implements PlayerRepository
{
   private static final String resourceName = "/players.csv";
   private Map<String, Player> playerById = Maps.newConcurrentMap();
   private static Logger  logger = LoggerFactory.getLogger(PlayerRepositoryImpl.class);

   private Player fromLine(String[] line)
   {
      Player m = new Player();
      m.setName(line[0]);
      m.setEmail(line[1]);
      List<String> instruments = Splitter.on(',').splitToList(line[2]);
      for (String i : instruments)
      {
         Instrument instr = Instrument.valueOf(i);
         if (i != null)
            m.addPlayedInstrument(instr);
      }
      return m;
   }

   @Override
   @LogPerformances(layer = "domain", operation = "find_player_by_id")
   public Player find(String id)
   {
      return playerById.get(id);
   }

   @SuppressWarnings("resource")
   @LogPerformances(layer = "domain", operation = "loadFile")
   protected void loadFile()
   {
      try
      {
         URL resource = PlayerRepositoryImpl.class.getResource(resourceName);
         CSVReader reader = new CSVReader(new FileReader(resource.getFile()), ';');
         String[] line;
         while ((line = reader.readNext()) != null)
         {
            logger.info("Loading record '{}'",Joiner.on("//").join(line));
            Player m = fromLine(line);
            playerById.put(m.getId(), m);

         }
      }
      catch (IOException e)
      {
         throw new RuntimeException("Unable to read resource;" + resourceName);
      }
   }

   public PlayerRepositoryImpl()
   {
      loadFile();
   }


   @Override
   @LogPerformances(layer = "domain", operation = "find_player_by_instrument")
   public List<Player> find(Instrument instrument)
   {
      List<Player> players = Lists.newArrayList();
      for (Map.Entry<String, Player> entry : playerById.entrySet())
      {
         if (entry.getValue().getInstruments().contains(instrument))
            players.add(entry.getValue());
      }
      return players;
   }

   @Override
   @LogPerformances(layer = "domain", operation = "find_player_by_instrument_category")
   public List<Player> find(Category instrumentCategory)
   {
      Set<Player> players = Sets.newHashSet();

      for (Map.Entry<String, Player> entry : playerById.entrySet())
      {
         for (Instrument inst : entry.getValue().getInstruments())
         {
            if (inst.getCategory().equals(instrumentCategory))
               players.add(entry.getValue());
         }
      }
      return Lists.newArrayList(players);
   }

   @Override
   @LogPerformances(layer = "domain", operation = "find_all_players")
   public List<Player> list()
   {
      return ImmutableList.copyOf(playerById.values());
   }
}
