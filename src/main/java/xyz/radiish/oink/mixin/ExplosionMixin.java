package xyz.radiish.oink.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraft.world.explosion.EntityExplosionBehavior;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.radiish.oink.origins.TheBlade;

import java.util.ArrayList;
import java.util.List;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {
  @Mutable @Shadow @Final private float power;

  @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/explosion/Explosion$DestructionType;)V")
  private void onInit(World world, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior explosionBehavior, double d, double e, double f, float g, boolean bl, Explosion.DestructionType destructionType, CallbackInfo ci) {
    List<Entity> entities = new ArrayList<>();
    if(damageSource != null) {
      if(damageSource.getAttacker() != null) {
        entities.add(damageSource.getAttacker());
      }
      if(damageSource.getSource() != null) {
        entities.add(damageSource.getSource());
      }
    }
    if(entity instanceof TntEntity) {
      entities.add(((TntEntity) entity).getCausingEntity());
    }
    if(entities.stream().anyMatch(TheBlade.ANARCHY::isActive)) {
      power *= 2;
    }
  }
}
