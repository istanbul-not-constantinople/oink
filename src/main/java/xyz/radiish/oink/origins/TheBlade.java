package xyz.radiish.oink.origins;

import io.github.apace100.origins.power.Power;
import io.github.apace100.origins.power.PowerType;
import io.github.apace100.origins.power.PowerTypeReference;
import net.minecraft.util.Identifier;

public class TheBlade {
  public static final PowerType<Power> NEVER_DIES  = new PowerTypeReference<>(new Identifier("oink", "the_blade/never_dies"));
  public static final PowerType<Power> PREPARATION = new PowerTypeReference<>(new Identifier("oink", "the_blade/preparation"));
  public static final PowerType<Power> ANARCHY     = new PowerTypeReference<>(new Identifier("oink", "the_blade/anarchy"));
  public static final PowerType<Power> SNOB        = new PowerTypeReference<>(new Identifier("oink", "the_blade/snob"));
}
