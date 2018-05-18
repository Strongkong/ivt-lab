package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore torpedostore;	
  private TorpedoStore torpedostore2;

  @Before
  public void init(){
    this.torpedostore = mock(TorpedoStore.class);
    this.torpedostore2 = mock(TorpedoStore.class);
    this.ship = new GT4500(torpedostore, torpedostore2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(torpedostore.fire(1)).thenReturn(true)  ;  
    when(torpedostore.isEmpty()).thenReturn(false);

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
    when(torpedostore2.fire(1)).thenReturn(true);
    when(torpedostore.isEmpty()).thenReturn(false);
    when(torpedostore2.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(torpedostore, times(1)).fire(1);
    verify(torpedostore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Empty(){
    // Arrange
    when(torpedostore.fire(1)).thenReturn(true) ;
    when(torpedostore.isEmpty()).thenReturn(true);
    when(torpedostore2.fire(1)).thenReturn(true) ;
    when(torpedostore2.isEmpty()).thenReturn(true);     

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(torpedostore, times(0)).fire(1);
    verify(torpedostore2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_2nd_empty(){
    // Arrange
    when(torpedostore.fire(1)).thenReturn(true) ;
    when(torpedostore.isEmpty()).thenReturn(false);
    when(torpedostore2.fire(1)).thenReturn(true) ;
    when(torpedostore2.isEmpty()).thenReturn(true);  

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE); 
    
    // Assert
    assertEquals(true, result);
    verify(torpedostore, times(1)).fire(1);
    verify(torpedostore2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Firstfail(){
    // Arrange
    when(torpedostore.fire(1)).thenReturn(false) ;
    when(torpedostore.isEmpty()).thenReturn(false);
    when(torpedostore2.fire(1)).thenReturn(true) ;
    when(torpedostore2.isEmpty()).thenReturn(false);   

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(torpedostore, times(1)).fire(1);
    verify(torpedostore2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_2nd_fail(){
    // Arrange
    when(torpedostore.fire(1)).thenReturn(true) ;
    when(torpedostore.isEmpty()).thenReturn(false);
    when(torpedostore2.fire(1)).thenReturn(false) ;
    when(torpedostore2.isEmpty()).thenReturn(false); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(false, result2);
    verify(torpedostore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Empty(){
    // Arrange
    when(torpedostore.fire(1)).thenReturn(true) ;
    when(torpedostore.isEmpty()).thenReturn(true);
    when(torpedostore2.fire(1)).thenReturn(false) ;
    when(torpedostore2.isEmpty()).thenReturn(true); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(torpedostore, times(0)).fire(1);
    verify(torpedostore2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_First_Empty(){
    // Arrange
    when(torpedostore.fire(1)).thenReturn(true) ;
    when(torpedostore.isEmpty()).thenReturn(true);
    when(torpedostore2.fire(1)).thenReturn(true) ;
    when(torpedostore2.isEmpty()).thenReturn(false); 

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(torpedostore, times(0)).fire(1);
    verify(torpedostore2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_2nd_Empty(){
    // Arrange
    when(torpedostore.fire(1)).thenReturn(true) ;
    when(torpedostore.isEmpty()).thenReturn(false);
    when(torpedostore2.fire(1)).thenReturn(true) ;
    when(torpedostore2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(torpedostore, times(1)).fire(1);
    verify(torpedostore2, times(0)).fire(1);
  }

  @Test
  public void firelaser(){
    // Arrange

    // Act
    boolean result = ship.fireLaser(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);

  }
  


}
