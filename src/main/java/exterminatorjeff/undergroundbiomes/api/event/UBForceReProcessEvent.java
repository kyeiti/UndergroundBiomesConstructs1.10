package exterminatorjeff.undergroundbiomes.api.event;

import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

/**
 * @author Aang23
 * 
 * Simple event other mods can call to force-reprocess a chunk & its ores
 */
@Cancelable
public class UBForceReProcessEvent extends ChunkEvent {
    public UBForceReProcessEvent(Chunk chunk) {
        super(chunk);
    }

    public class Ores extends UBForceReProcessEvent {
        public Ores(Chunk chunk) {
            super(chunk);
        }
    }

    public class All extends UBForceReProcessEvent {
        public All(Chunk chunk) {
            super(chunk);
        }
    }
}