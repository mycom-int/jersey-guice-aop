package com.mycom.samples.aop.interfaces.dto.v1;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.mycom.samples.aop.domain.model.Player;

public class DtoAssembler
{
   public static DtoAssembler get()
   {
      return new DtoAssembler();
   }

   public PlayerDto toDto(Player player)
   {
      PlayerDto dto = new PlayerDto();
      dto.setName(player.getName());
      dto.setEmail(player.getEmail());
      dto.setInstruments(Joiner.on(",").join(player.getInstruments()));
      return dto;
   }

   public List<PlayerDto> toDto(List<Player> players)
   {
      List<PlayerDto> ret = Lists.newArrayList();
      for (Player p : players)
         ret.add(toDto(p));
      return ret;
   }

}
