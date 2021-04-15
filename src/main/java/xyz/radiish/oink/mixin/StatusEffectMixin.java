package xyz.radiish.oink.mixin;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.radiish.oink.origins.TheBlade;

@Mixin(StatusEffect.class)
public abstract class StatusEffectMixin {

  @Shadow public abstract void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity);

  private boolean badCoding;

  @Inject(at = @At("HEAD"), method = "applyInstantEffect", cancellable = true)
  private void onAddStatusEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity, CallbackInfo ci) {
    if(!badCoding && TheBlade.PREPARATION.isActive(target)) {
      badCoding = true;
      applyInstantEffect(source, attacker, target, (amplifier + 1) * 2 - 1, proximity);
      badCoding = false;
      ci.cancel();
    }
  }
}
