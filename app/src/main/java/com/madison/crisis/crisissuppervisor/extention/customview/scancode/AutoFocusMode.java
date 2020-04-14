
package com.madison.crisis.crisissuppervisor.extention.customview.scancode;

/**
 * Code scanner auto focus mode
 *
 * @see CodeScanner#setAutoFocusMode(AutoFocusMode)
 */
public enum AutoFocusMode {
    /**
     * Auto focus camera with the specified interval
     *
     * @see CodeScanner#setAutoFocusInterval(long)
     */
    SAFE,

    /**
     * Continuous auto focus, may not work on some devices
     */
    CONTINUOUS
}
