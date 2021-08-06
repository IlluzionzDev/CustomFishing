package com.illuzionzstudios.customfishing.reward.template.yaml;

/**
 * A default reward template for if no
 * rewards exist
 */
public abstract class DefaultRewardTemplate extends YAMLRewardTemplate {

    /**
     * @param fileName  name of reward file in '/rewards'
     * @param directory The directory of the template
     */
    public DefaultRewardTemplate(String fileName, String directory) {
        super(fileName, directory);
    }

    /**
     * Here save default reward to file
     */
    @Override
    public boolean save() {
        // Set defaults then save
        setDefaults();
        super.save();
        return true;
    }

    /**
     * Set the default rewards for this template
     */
    public abstract void setDefaults();
}
