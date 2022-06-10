package com.chimericdream.flatbedrock.mixin;

import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VanillaSurfaceRules.class)
public class FlatBedrockMixin {
	@Redirect(
		method = "createDefaultRule",
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/gen/surfacebuilder/MaterialRules.verticalGradient (Ljava/lang/String;Lnet/minecraft/world/gen/YOffset;Lnet/minecraft/world/gen/YOffset;)Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$MaterialCondition;",
			ordinal = 0
		)
	)
	private static MaterialRules.MaterialCondition flattenBedrockRoof(String id, YOffset oldOffset, YOffset roofLayer) {
		return MaterialRules.verticalGradient(id, roofLayer, roofLayer);
	}

	@Redirect(
		method = "createDefaultRule",
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/gen/surfacebuilder/MaterialRules.verticalGradient (Ljava/lang/String;Lnet/minecraft/world/gen/YOffset;Lnet/minecraft/world/gen/YOffset;)Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$MaterialCondition;",
			ordinal = 1
		)
	)
	private static MaterialRules.MaterialCondition flattenBedrockFloor(String id, YOffset floorLayer, YOffset oldOffset) {
		return MaterialRules.verticalGradient(id, floorLayer, floorLayer);
	}

	@Redirect(
		method = "createNetherSurfaceRule",
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/gen/surfacebuilder/MaterialRules.verticalGradient (Ljava/lang/String;Lnet/minecraft/world/gen/YOffset;Lnet/minecraft/world/gen/YOffset;)Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$MaterialCondition;",
			ordinal = 0
		)
	)
	private static MaterialRules.MaterialCondition flattenNetherFloor(String id, YOffset floorLayer, YOffset oldOffset) {
		return MaterialRules.verticalGradient(id, floorLayer, floorLayer);
	}

	@Redirect(
		method = "createNetherSurfaceRule",
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/world/gen/surfacebuilder/MaterialRules.verticalGradient (Ljava/lang/String;Lnet/minecraft/world/gen/YOffset;Lnet/minecraft/world/gen/YOffset;)Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$MaterialCondition;",
			ordinal = 1
		)
	)
	private static MaterialRules.MaterialCondition flattenNetherRoof(String id, YOffset oldOffset, YOffset roofLayer) {
		return MaterialRules.verticalGradient(id, YOffset.belowTop(1), roofLayer);
	}
}
