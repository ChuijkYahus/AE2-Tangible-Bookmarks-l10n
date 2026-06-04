package me.myogoo.ae2tb.init;

import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.WidgetContainer;
import me.myogoo.ae2tb.client.TranslateKey;
import me.myogoo.myotus.api.config.MyoConfigTabScreen;
import me.myogoo.myotus.client.gui.widgets.KeyBindingButton;

public class AE2TBConfigTab implements MyoConfigTabScreen {
    @Override
    public void buildTab(WidgetContainer widget, AEBaseScreen<?> screen) {
        widget.add("binding:pickup_single", new KeyBindingButton(TranslateKey.PICKUP_SINGLE_ITEM.getTranslate(), keys -> {}));
        widget.add("binding:pickup_set", new KeyBindingButton(TranslateKey.PICKUP_SET_ITEM.getTranslate(), keys -> {}));
        widget.add("binding:picked_autocrafting", new KeyBindingButton(TranslateKey.PICKED_ITEM_AUTOCRAFTING.getTranslate(), keys -> {}));
    }
}
