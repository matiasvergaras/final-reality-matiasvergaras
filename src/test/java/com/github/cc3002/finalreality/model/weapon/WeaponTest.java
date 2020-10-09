package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeaponTest {

  private static final String AXE_NAME = "Test Axe";
  private static final String STAFF_NAME = "Test Staff";
  private static final String SWORD_NAME = "Test Sword";
  private static final String BOW_NAME = "Test Bow";
  private static final String KNIFE_NAME = "Test Knife";
  private static final int DAMAGE = 15;
  private static final int WEIGHT = 10;
  private static final int MAGIC_DAMAGE = 30;

  private Axe testAxe;
  private Staff testStaff;
  private Sword testSword;
  private Bow testBow;
  private Knife testKnife;

  @BeforeEach
  void setUp() {
    testAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    testStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
    testSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
    testBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    testKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
  }
  /**
   * Checks that the class' constructor and hashcode, equals methods works properly.
   */
  @Test
  void constructorTest() {
    var expectedAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    var expectedStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
    var expectedSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
    var expectedBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    var expectedKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);



    assertEquals(expectedAxe, testAxe);
    assertEquals(expectedAxe.hashCode(), testAxe.hashCode());
    assertNotEquals(expectedAxe.hashCode(), testStaff.hashCode());

    assertEquals(expectedStaff, testStaff);
    assertEquals(expectedStaff.hashCode(), testStaff.hashCode());
    assertNotEquals(expectedStaff.hashCode(), testSword.hashCode());

    assertEquals(expectedSword, testSword);
    assertEquals(expectedSword.hashCode(), testSword.hashCode());
    assertNotEquals(expectedSword.hashCode(), testBow.hashCode());

    assertEquals(expectedBow, testBow);
    assertEquals(expectedBow.hashCode(), testBow.hashCode());
    assertNotEquals(expectedBow.hashCode(), testKnife.hashCode());

    assertEquals(expectedKnife, testKnife);
    assertEquals(expectedKnife.hashCode(), testKnife.hashCode());
    assertNotEquals(expectedKnife.hashCode(), testAxe.hashCode());
  }

  protected void checkConstruction(final IWeapon expectedWeapon,
                                   final IWeapon testEqualWeapon,
                                   final IWeapon sameClassDifferentWeapon,
                                   final IWeapon differentWeapon){
    assertEquals(expectedWeapon, testEqualWeapon);
    assertNotEquals(sameClassDifferentWeapon, testEqualWeapon);
    assertNotEquals(testEqualWeapon, differentWeapon);
    assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());

  }

}