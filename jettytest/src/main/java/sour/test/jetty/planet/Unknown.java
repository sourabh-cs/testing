package sour.test.jetty.planet;

import org.springframework.stereotype.Component;

@Component
public class Unknown implements Planet {
  
  private final String  name     = "Unknown";
  private final Integer moons    = -1;
  private final Integer diameter = -1;
                                 
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
