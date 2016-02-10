package sour.test.jetty.planet;

import org.springframework.stereotype.Component;

@Component
public class Neptune implements Planet {
  
  private final String name     = "Neptune";
  private final int    moons    = 13;
  private final int    diameter = 49532;
                                
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
