package xyz.radiish.oink.mixin;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.radiish.oink.origins.TheBlade;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
  @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);
  private boolean badCoding;

  @Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
  private void onTryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
    if(TheBlade.NEVER_DIES.isActive((LivingEntity) (Object) this)) {
      cir.setReturnValue(false);
    }
  }

  @Inject(at = @At("HEAD"), method = "addStatusEffect", cancellable = true)
  private void onAddStatusEffect(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
    LivingEntity me = (LivingEntity) (Object) this;
    if(!badCoding && TheBlade.PREPARATION.isActive(me)) {
      StatusEffectInstance statusEffect = new StatusEffectInstance(effect.getEffectType(), effect.getDuration(), (effect.getAmplifier() + 1) * 2 - 1, effect.isAmbient(), effect.shouldShowParticles(), effect.shouldShowIcon());
      badCoding = true;
      addStatusEffect(statusEffect);
      badCoding = false;
      cir.cancel();
    }
  }
}
