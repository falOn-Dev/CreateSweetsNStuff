package io.github.falondev.createsweetsnstuff.processing.fan;

import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public class ChillingRecipe extends ProcessingRecipe<ChillingRecipe.ChillingWrapper> {
    public ChillingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(ChillingRecipeTypes.CHILLING, params);
    }

    @Override
    protected int getMaxInputCount() {
        return 1;
    }

    @Override
    protected int getMaxOutputCount() {
        return 12;
    }

    @Override
    public boolean matches(ChillingWrapper chillingWrapper, Level level) {
        if(chillingWrapper.isEmpty()) return false;
        return ingredients.getFirst().test(chillingWrapper.getItem(0));
    }

    public static class ChillingWrapper extends RecipeWrapper {

        public ChillingWrapper() {
            super(new ItemStackHandler(1));
        }
    }
}
