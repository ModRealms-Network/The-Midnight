package midnight.client.biome;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.level.ColorResolver;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.IntSupplier;

public class BiomeColorCache {
    private final Object2ObjectLinkedOpenHashMap<SectionPos, int[]> cache = new Object2ObjectLinkedOpenHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public int getColor(BlockPos pos, ColorResolver resolver) {
        if(Minecraft.getInstance().world == null) return 0xFFFFFF;
        return getColor(pos, () -> Minecraft.getInstance().world.getBlockColorRaw(pos, resolver));
    }

    public int getColor(BlockPos pos, IntSupplier supplier) {
        SectionPos sectPos = SectionPos.from(pos);

        lock.readLock().lock();
        int[] cacheBuf = cache.get(sectPos);
        lock.readLock().unlock();

        if(cacheBuf == null) {
            cacheBuf = new int[4096];
            Arrays.fill(cacheBuf, -1);
            lock.writeLock().lock();
            cache.putAndMoveToFirst(sectPos, cacheBuf);
            if(cache.size() > Runtime.getRuntime().availableProcessors() * 16) {
                cache.removeLast();
            }
            lock.writeLock().unlock();
        } else {
            lock.writeLock().lock();
            cache.putAndMoveToFirst(sectPos, cacheBuf);
            lock.writeLock().unlock();
        }

        int x = pos.getX() & 15;
        int y = pos.getY() & 15;
        int z = pos.getZ() & 15;

        int i = (x * 16 + z) * 16 + y;

        lock.readLock().lock();
        int cached = cacheBuf[i];
        lock.readLock().unlock();

        if(cached < 0) {
            cached = supplier.getAsInt();

            lock.writeLock().lock();
            cacheBuf[i] = cached;
            lock.writeLock().unlock();
        }

        return cached;
    }

    public void reset() {
        cache.clear();
    }
}
