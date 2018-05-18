package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore torpedostore;	

  @Before
  public void init(){
    this.torpedostore = mock(TorpedoStore.class);
    this.ship = new GT4500(torpedostore, torpedostore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(torpedostore.fire(1)).thenReturn(true)  ;  

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(torpedostore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(torpedostore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(torpedostore, times(2)).fire(1);
  }

}
