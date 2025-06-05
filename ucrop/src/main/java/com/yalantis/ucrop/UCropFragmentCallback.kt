package com.yalantis.ucrop

import com.yalantis.ucrop.model.AspectRatio
import com.yalantis.ucrop.model.UCropResult

interface UCropFragmentCallback {
    /**
     * Return loader status
     * @param showLoader
     */
    fun loadingProgress(showLoader: Boolean)

    /**
     * Return cropping result or error
     * @param result
     */
    fun onCropFinish(result: UCropResult)


    fun onCustomAspectRatioClicked(custom: AspectRatio.Custom, callback: (Pair<Float, Float>) -> Unit)
}
