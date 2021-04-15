package xyz.radiish.oink.mixinhelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.BadRespawnPointDamageSource;
import org.jetbrains.annotations.Nullable;

public class BadRespawnPointEntityDamageSource extends BadRespawnPointDamageSource {
  private final Entity source;
  public BadRespawnPointEntityDamageSource(Entity source) {
    super();
    this.source = source;
  }

  @Override
  public @Nullable Entity getSource() {
    return source;
  }
}
