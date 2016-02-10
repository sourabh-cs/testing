package sour.test.jetty.planet;

import org.springframework.stereotype.Component;

@Component
public class Earth implements Planet {
  
  private final String name     = "Earth";
  private final int    moons    = 1;
  private final int    diameter = 12760;
                                
  public String getName()
  {
    return name;
  }
  
  public int getMoons()
  {
    return moons;
  }
  
  public int getDiameter()
  {
    return diameter;
  }
  
}
