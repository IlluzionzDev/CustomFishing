/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
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
