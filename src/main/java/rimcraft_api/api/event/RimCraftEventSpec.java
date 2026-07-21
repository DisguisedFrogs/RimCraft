package rimcraft_api.api.event;

/**
 * RimCraft 事件命名规范常量。
 *
 * <p>命名约定：事件类名 = 领域前缀 + 动作 + 时态（如 {@code NeedTierCrossedEvent} = Need + TierCrossed + 完成时态），
 * 全项目统一登记，新增事件按此规范命名并归入对应领域。
 */
public final class RimCraftEventSpec {

    /** 单位领域前缀（生成/就绪/移除/死亡等生命周期事件） */
    public static final String PAWN = "Pawn";

    /** 需求领域前缀（需求值变化/档位跃迁） */
    public static final String NEED = "Need";

    /** 心情领域前缀（恶化预警/崩溃） */
    public static final String MOOD = "Mood";

    /** 派系领域前缀（好感变化/敌对状态切换，归 M2 里程碑） */
    public static final String FACTION = "Faction";

    /** 系统领域前缀（研究完成/事件触发前/数据重载完成等） */
    public static final String SYSTEM = "System";

    private RimCraftEventSpec() {
    }
}
