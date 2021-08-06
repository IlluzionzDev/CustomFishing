package com.illuzionzstudios.customfishing.reward.template.loader;

import com.illuzionzstudios.customfishing.reward.template.AbstractRewardTemplate;

import java.util.Map;

/**
 * Load templates from a directory
 */
public interface AbstractRewardLoader {

    /**
     * Load reward templates from directory
     *
     * @return Map of file names and templates
     */
    Map<String, AbstractRewardTemplate> loadTemplates();

}
