package com.mycom.samples.aop.domain;

import java.util.List;

import com.mycom.samples.aop.domain.model.Instrument;
import com.mycom.samples.aop.domain.model.Instrument.Category;
import com.mycom.samples.aop.domain.model.Player;

/**
 * Repository of known musicians
 */
public interface PlayerRepository
{
   /** find the player by id */
   Player find(String id);
   /** find all musician playing a given instrument */
   List<Player> find(Instrument instrument);
   /** find all musician playing a given category of instruments */
   List<Player> find(Category instrumentCategory);
   /** lists all known musicians */
   List<Player> list();
}

