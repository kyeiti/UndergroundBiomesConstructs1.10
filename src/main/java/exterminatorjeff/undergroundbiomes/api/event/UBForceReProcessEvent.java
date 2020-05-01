package exterminatorjeff.undergroundbiomes.api.event;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
/**
 * @author Aang23
 * 
 * Simple event other mods can call to force-reprocess a chunk & its ores
 */
public class UBForceReProcessEvent extends PopulateChunkEvent {
    public UBForceReProcessEvent(IChunkGenerator gen, World world, Random rand, int chunkX, int chunkZ, boolean hasVillageGenerated) {
        super(gen, world, rand, chunkX, chunkZ, hasVillageGenerated);
    }
}