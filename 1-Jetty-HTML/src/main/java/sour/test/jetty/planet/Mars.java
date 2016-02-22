package sour.test.jetty.planet;

import org.springframework.stereotype.Component;

@Component
public class Mars implements Planet {
  
  private final String name     = "Mars";
  private final int    moons    = 2;
  private final int    diameter = 6746;
                                
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
