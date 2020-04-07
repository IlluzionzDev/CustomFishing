/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
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
