package com.mycom.samples.aop.interfaces.rest.v1;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.mycom.samples.aop.annotations.LogPerformances;
import com.mycom.samples.aop.application.MusicianFinderApp;
import com.mycom.samples.aop.domain.model.Instrument.Category;
import com.mycom.samples.aop.interfaces.dto.v1.DtoAssembler;
import com.mycom.samples.aop.interfaces.dto.v1.PlayerDto;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/players")
public class PlayersResource
{
   private static Logger logger = LoggerFactory.getLogger(PlayersResource.class);

   @Inject
   MusicianFinderApp application;

   @GET
   @LogPerformances(layer="REST",operation="list_all_players")
   public List<PlayerDto> listAll()
   {
      List<PlayerDto> ret = Lists.newArrayList();
      ret.addAll(DtoAssembler.get().toDto(application.listMusicians()));
      return ret;
   }

   @GET
   @LogPerformances(layer="REST",operation="list_all_players_by_category")
   @Path("/{category}")
   public List<PlayerDto> listByCategory(@PathParam("category") String category)
   {
      List<PlayerDto> ret = Lists.newArrayList();
      try
      {
         Category categ = Category.valueOf(category);
         if (categ!=null)
           ret.addAll(DtoAssembler.get().toDto(application.findMusician(categ)));
      }
      catch(IllegalArgumentException e)
      {
           logger.error("Unable to list player of category;"+category+";error;"+e.getMessage());
      }
      return ret;
   }



}
