/**
 * Copyright © 2020 Property of Illuzionz Studios, LLC
 * All rights reserved. No part of this publication may be reproduced, distributed, or
 * transmitted in any form or by any means, including photocopying, recording, or other
 * electronic or mechanical methods, without the prior written permission of the publisher,
 * except in the case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. Any licensing of this software overrides
 * this statement.
 */
package com.illuzionzstudios.customfishing.command.sub;

import com.illuzionzstudios.mist.command.SpigotSubCommand;

/**
 * Open up the admin gui to configure rewards
 */
public class AdminCommand extends SpigotSubCommand {

    public AdminCommand() {
        super("admin", "config");

        setDescription("Open the GUI to configure rewards");
    }

    @Override
    protected void onCommand() {
        checkConsole();
        // TODO: Open new config UI
    }
}
