package com.mycom.samples.aop.application;

import java.util.List;

import com.mycom.samples.aop.domain.model.Instrument.Category;
import com.mycom.samples.aop.domain.model.Player;


/** Trivial musician finder application */
public interface MusicianFinderApp
{
   /** find a musician playing a given category of instruments */
   List<Player> findMusician(Category category);
   /** find all musicians */
   List<Player> listMusicians();
}


