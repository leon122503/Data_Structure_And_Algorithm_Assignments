package cosc201.a2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.text.WrappedPlainView;

/**
 * A potionarium is a collection of drawers, each of which contains a set of
 * ingredients.
 * 
 * For Assignment 2, you will implement this interface. See the full assignment
 * for further details.
 * 
 * Your code will be marked for correctness (60%) and efficiency (40%).
 * 
 * You may not change the method signatures, but you may add additional methods
 * and/or data
 * fields.
 * 
 */
public class Potionarium {

  private Map<Long, Set<String>> drawers;
  private Map<String, Set<Long>> Inventory;

  /**
   * Creates a new empty potionarium.
   */
  public Potionarium() {
    drawers = new HashMap<>();
    Inventory = new HashMap<>();
  };

  /**
   * Determines the inventory of the potionarium.
   * 
   * @return The set of ingredients in the potionarium.
   */
  public Set<String> getInventory() {
    if (Inventory.keySet() == null) {
      return new HashSet<String>();
    }
    Set<String> output = new HashSet<>(Inventory.keySet());
    return output;
  }

  /**
   * Determines the drawers that contain an ingredient.
   * 
   * @param ingredient The ingredient to be searched for.
   * @return The set of drawers that contain the ingredient.
   */
  public Set<Long> getDrawers(String ingredient) {
    if (Inventory.get(ingredient) == null) {
      return new HashSet<Long>();
    }
    Set<Long> output = new HashSet<>(Inventory.get(ingredient));
    return output;
  }

  /**
   * Determines the ingredients that are in a drawer.
   * 
   * @param drawer The drawer to be searched for.
   * @return The set of ingredients that are in the drawer.
   */
  public Set<String> getIngredients(long drawer) {
    if (drawers.get(drawer) == null) {
      return new HashSet<String>();
    }
    Set<String> output = new HashSet<>(drawers.get(drawer));
    return output;
  }

  /**
   * Fills the given drawer with the set of ingredients given, provided that it
   * was
   * empty. If the drawer was not empty, it is not changed.
   * 
   * @param drawer      The number of the drawer to be filled
   * @param ingredients The set of ingredients to be added
   * @return True if the drawer was filled, false if it was not empty
   */
  public boolean fillDrawer(long drawer, Set<String> ingredients) {
    Set<String> IngredientsCopty = new HashSet<>(ingredients);
    if (drawers.get(drawer) == null) {
      drawers.put(drawer, IngredientsCopty);
      for (String s : IngredientsCopty) {
        if (Inventory.get(s) == null) {
          Set<Long> hs = new HashSet<>();
          hs.add(drawer);
          Inventory.put(s, hs);
        } else {
          Inventory.get(s).add(drawer);
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Adds an ingredient to a drawer. If the ingredient is already present, it is
   * not added again. If the drawer does not exist, it is created.
   * 
   * @param drawer     The number of the drawer to be added to.
   * @param ingredient The ingredient to be added.
   * @return True if the ingredient was added, false if it was already present.
   */
  public boolean addIngredient(long drawer, String ingredient) {
    Set<String> newdrawer = drawers.get(drawer);
    if (newdrawer == null) {
      Set<String> hs = new HashSet<>();
      hs.add(ingredient);
      drawers.put(drawer, hs);
      if (Inventory.get(ingredient) == null) {
        Set<Long> hs2 = new HashSet<>();
        hs2.add(drawer);
        Inventory.put(ingredient, hs2);
      } else if (Inventory.get(ingredient) != null) {
        Inventory.get(ingredient).add(drawer);
      }
      return true;
    }
    if (newdrawer.contains(ingredient))
      return false;
    if (Inventory.get(ingredient) == null) {
      Set<Long> hs2 = new HashSet<>();
      hs2.add(drawer);
      Inventory.put(ingredient, hs2);
    } else if (Inventory.get(ingredient) != null) {
      Inventory.get(ingredient).add(drawer);
    }
    newdrawer.add(ingredient);
    return true;
  }

  /**
   * Removes an ingredient from a drawer.
   * 
   * @param drawer     The number of the drawer to be removed from.
   * @param ingredient The ingredient to be removed.
   * @return True if the ingredient was removed, false if it was not present.
   */
  public boolean removeIngredient(long drawer, String ingredient) {
    if (drawers.get(drawer) != null) {
      if (drawers.get(drawer).contains(ingredient) == true) {
        drawers.get(drawer).remove(ingredient);
        Inventory.get(ingredient).remove(drawer);
        if (Inventory.get(ingredient).size() == 0) {
          Inventory.remove(ingredient);
        }
        if (drawers.get(drawer).size() == 0) {
          drawers.remove(drawer);
        }
        return true;
      }

    }
    return false;
  }

  /**
   * Removes a set of ingredients from a drawer. If one or more are
   * missing, then none should be removed.
   * 
   * @param drawer      The number of the drawer to be removed from.
   * @param ingredients The set of ingredients to be removed.
   * @return True if all ingredients were removed, false if one or more were
   *         missing.
   */
  public boolean removeIngredients(long drawer, Set<String> ingredients) {
    if (drawers.get(drawer).containsAll(ingredients) == true) {
      for (String s : ingredients) {
        Inventory.get(s).remove(drawer);
        if (Inventory.get(s).isEmpty() == true) {
          Inventory.remove(s);
        }
      }
      drawers.get(drawer).removeAll(ingredients);
      if (drawers.get(drawer).isEmpty() == true) {
        drawers.remove(drawer);
      }
      return true;
    }
    return false;
  }
}
