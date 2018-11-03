package thegoldenproof.saomod.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import thegoldenproof.saomod.util.handlers.RegistryHandler;

public class SaoModTab extends CreativeTabs{

	public SaoModTab(String label) {
		super(label);
		setBackgroundImageName("saomod.png");
		
	}

	@Override
	public ItemStack getTabIconItem() {
		int totalItems = RegistryHandler.ITEMS.size();
		if (Math.random() <= RegistryHandler.BLOCKS.size() / totalItems) {
			int rand = (int)(Math.random()*(RegistryHandler.BLOCKS.size()-1.0));
			return new ItemStack(RegistryHandler.BLOCKS.get(rand));
		} else {
			int rand = (int)(Math.random()*(RegistryHandler.ITEMS.size()-1.0));
			return new ItemStack(RegistryHandler.ITEMS.get(rand));
		}
	}
	
	@Override
	public boolean hasSearchBar()
    {
        return getTabIndex() == CreativeTabs.SEARCH.getTabIndex() || getTabIndex() == this.getTabIndex();
    }

}
