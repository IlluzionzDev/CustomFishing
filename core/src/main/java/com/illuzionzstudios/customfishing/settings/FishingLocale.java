/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.settings;

import com.illuzionzstudios.mist.config.locale.Locale;
import com.illuzionzstudios.mist.plugin.SpigotPlugin;

public class FishingLocale extends Locale {

    public FishingLocale(SpigotPlugin plugin) {
        super(plugin);
    }

    @Override
    protected int getConfigVersion() {
        return 1;
    }

    @Override
    public void loadLocale() {

    }
}
