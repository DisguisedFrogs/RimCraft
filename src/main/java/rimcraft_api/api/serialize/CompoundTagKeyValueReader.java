package rimcraft_api.api.serialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

/**
 * 基于 {@link CompoundTag} 的 {@link IKeyValueReader} 默认实现。
 *
 * <p>属契约基础设施，与 {@link CompoundTagKeyValueWriter} 配对；缺失容错语义见接口契约。
 */
public final class CompoundTagKeyValueReader implements IKeyValueReader {

    private final CompoundTag tag;

    /**
     * @param tag 底层承载读取的 NBT 复合标签（不得为 null；只读访问，不修改该实例）
     */
    public CompoundTagKeyValueReader(CompoundTag tag) {
        this.tag = Objects.requireNonNull(tag, "tag");
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return tag.getIntOr(key, defaultValue);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return tag.getDoubleOr(key, defaultValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return tag.getBooleanOr(key, defaultValue);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return tag.getStringOr(key, defaultValue);
    }

    @Override
    public boolean contains(String key) {
        return tag.contains(key);
    }

    @Override
    public Optional<IKeyValueReader> child(String key) {
        return tag.getCompound(key).map(CompoundTagKeyValueReader::new);
    }

    @Override
    public List<IKeyValueReader> childList(String key) {
        Optional<ListTag> list = tag.getList(key);
        if (list.isEmpty()) {
            return List.of();
        }
        ListTag entries = list.get();
        List<IKeyValueReader> result = new ArrayList<>(entries.size());
        for (int i = 0; i < entries.size(); i++) {
            entries.getCompound(i).ifPresent(element -> result.add(new CompoundTagKeyValueReader(element)));
        }
        return List.copyOf(result);
    }
}
