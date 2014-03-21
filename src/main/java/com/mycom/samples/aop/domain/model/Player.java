package com.mycom.samples.aop.domain.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import jersey.repackaged.com.google.common.collect.Sets;

import com.google.common.collect.ImmutableList;

/** The instrument player entity */
public class Player
{
   private String id = UUID.randomUUID().toString();
   private String name;
   Set<Instrument> instruments = Sets.newHashSet();
   private String email;

   public String getId()
   {
      return id;
   }
   public void setId(String id)
   {
      this.id = id;
   }
   public String getEmail()
   {
      return email;
   }
   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getName()
   {
      return name;
   }
   public void setName(String nickname)
   {
      this.name = nickname;
   }

   public List<Instrument> getInstruments()
   {
      return ImmutableList.copyOf(instruments);
   }

   public void addPlayedInstrument(Instrument instrument)
   {
      instruments.add(instrument);
   }

   public void removePlayedInstrument(Instrument instrument)
   {
      instruments.add(instrument);
   }

}
