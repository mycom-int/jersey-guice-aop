package com.mycom.samples.aop.interfaces.dto.v1;

public class PlayerDto
{
   private String name;
   private String email;
   private String instruments;
   public String getName()
   {
      return name;
   }
   public void setName(String name)
   {
      this.name = name;
   }
   public String getEmail()
   {
      return email;
   }
   public void setEmail(String email)
   {
      this.email = email;
   }
   public String getInstruments()
   {
      return instruments;
   }
   public void setInstruments(String instruments)
   {
      this.instruments = instruments;
   }
}
