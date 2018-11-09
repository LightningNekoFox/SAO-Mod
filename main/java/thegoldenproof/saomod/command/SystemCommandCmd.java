package thegoldenproof.saomod.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import thegoldenproof.saomod.items.ToolSword;
import thegoldenproof.saomod.util.IPriority;
import thegoldenproof.saomod.util.Reference;

public class SystemCommandCmd extends CommandBase implements ICommand {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "System_Call";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "Basically just for \"System Command! Generate Object: ID: Excalibur!\" and stuff";
	}
	
	
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		World world = sender.getEntityWorld();
		if (!world.isRemote && server.canUseCommand(server.getOpPermissionLevel(), getName())) {
			EntityPlayerMP player = getCommandSenderAsPlayer(sender);
			if (args[0].toLowerCase().equals("generate_object")) {
				if (args[1].toLowerCase().equals("ID:")) {
					Item item = getItemByText(sender, Reference.MOD_ID+":"+args[3]);
					if (item instanceof IPriority) {
						IPriority ipItem = ((IPriority) item);
						ipItem.setPriorityReqd(false);
						player.inventory.addItemStackToInventory(new ItemStack(ipItem.getItem()));
					}
				}
			}
		}
	}

}
