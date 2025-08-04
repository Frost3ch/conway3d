package net.frosty.conway3d.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.frosty.conway3d.item.ControllerItem;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class controllerGui extends LightweightGuiDescription {

    public void updateRules(WTextField newDR1, WTextField newDR2, WTextField newBR1, WTextField newBR2){
        if (!newDR1.getText().isBlank()){
            ControllerItem.deathRule1 = Integer.parseInt(newDR1.getText().strip());
        }
        if (!newDR2.getText().isBlank()) {
            ControllerItem.deathRule2 = Integer.parseInt(newDR2.getText().strip());
        }
        if (!newBR1.getText().isBlank()) {
            ControllerItem.bornRule1 = Integer.parseInt(newBR1.getText().strip());
        }
        if (!newBR2.getText().isBlank()) {
            ControllerItem.bornRule2 = Integer.parseInt(newBR2.getText().strip());
        }

    }

    public controllerGui() {
        WGridPanel root = new WGridPanel().setGaps(10,20);
        setRootPanel(root);
        root.setSize(270,120);

        WLabel deathRuleLabel1 = new WLabel(Text.of("Survival Min: "));
        root.add(deathRuleLabel1,1,1);
        WTextField deathRuleField1 = new WTextField(Text.of(ControllerItem.deathRule1.toString()));
        root.add(deathRuleField1,4,1);

        WLabel deathRuleLabel2 = new WLabel(Text.of("Survival Max: "));
        root.add(deathRuleLabel2,5,1);
        WTextField deathRuleField2 = new WTextField(Text.of(ControllerItem.deathRule2.toString()));
        root.add(deathRuleField2,8,1);

        WLabel bornRuleLabel1 = new WLabel(Text.of("Born Rule Min: "));
        root.add(bornRuleLabel1,1,2);
        WTextField bornRuleField1 = new WTextField(Text.of(ControllerItem.bornRule1.toString()));
        root.add(bornRuleField1,4,2);

        WLabel bornRuleLabel2 = new WLabel(Text.of("Born Rule Max: "));
        root.add(bornRuleLabel2,5,2);
        WTextField bornRuleField2 = new WTextField(Text.of(ControllerItem.bornRule2.toString()));
        root.add(bornRuleField2,8,2);

        WButton submitButton = new WButton(Text.of("Apply Rules"));
        submitButton.setOnClick(() -> {
            updateRules(deathRuleField1, deathRuleField2, bornRuleField1, bornRuleField2);
        });
        root.add(submitButton,3,3,4,1);

        WToggleButton toggleButton = new WToggleButton(Text.literal("2D-3D Toggle"));
        toggleButton.setToggle(ControllerItem.is3D);
        toggleButton.setOnToggle(on -> {
            if (on) {
                ControllerItem.is3D = true;
                System.out.println("3D mode toggled...");
            }
            else {
                ControllerItem.is3D = false;
                System.out.println("2D mode toggled...");
            }
        });

        root.add(toggleButton,3,4,4,1);




    }
}
