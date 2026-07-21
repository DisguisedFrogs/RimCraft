package rimcraft_api.api.event;

import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.common.NeoForge;

/**
 * RimCraft 全部自定义事件的抽象基类。
 *
 * <p>通道契约：全部 RimCraft 事件发布于 NeoForge game bus（{@link NeoForge#EVENT_BUS}），
 * 不另建私有总线；订阅走总线标准机制。
 *
 * <p>负载契约：事件只携带接口视图与不可变数据，不暴露实现类型；
 * 监听方不得假设事件源实体类型（玩家、村民、自定义生物均可能是棋子单位）。
 *
 * <p>本类为抽象基类，不可直接监听；各具体事件类的触发时机与取消语义见各自 Javadoc。
 */
public abstract class RimCraftEvent extends Event {

    protected RimCraftEvent() {
    }
}
