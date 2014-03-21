package com.mycom.samples.aop.domain.model;

public enum Instrument
{
   AcousticGuitar(Category.Chords),
   ElectricGuitar(Category.Chords),
   DoubleBass(Category.Chords),
   ElectricBass(Category.Chords),
   Rhodes(Category.Keys),
   Keyboard(Category.Keys),
   Piano(Category.Keys),
   TenorSax(Category.Brass),
   AltoSax(Category.Brass),
   Flute(Category.Brass),
   Trumpet(Category.Brass),
   Buggle(Category.Brass),
   Congas(Category.Percussions),
   Drums(Category.Percussions),
   LeadVoice(Category.Vocals),
   Choirs(Category.Vocals);

   private Category category;

   private Instrument(Category category)
   {
      this.category=category;
   }

   public Category getCategory()
   {
      return category;
   }

   public enum Category
   {
      Brass, Chords, Percussions, Keys, Vocals;
   }

}
