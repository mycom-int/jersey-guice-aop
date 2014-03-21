package com.mycom.samples.aop.interfaces.rest.v1;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jersey.repackaged.com.google.common.collect.Sets;

import com.google.common.collect.Lists;
import com.mycom.samples.aop.annotations.LogPerformances;
import com.mycom.samples.aop.domain.model.Instrument.Category;
import com.mycom.samples.aop.interfaces.dto.v1.DropDownDto;



@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/meta")
public class MetaResource
{
   private Set<DropDownDto> categories;

   public MetaResource()
   {
      categories = Sets.newHashSet();
      for (Category ic : Category.values())
      {
         DropDownDto dto = new DropDownDto();
         dto.setItem(ic.name());
         dto.setValue(ic.name());
         categories.add(dto);
      }
   }

   @GET
   @Path("/categories")
   @LogPerformances(layer = "REST", operation = "list_all_instrument_categories")
   public List<DropDownDto> getCategories()
   {
      return Lists.newArrayList(categories);
   }

}
