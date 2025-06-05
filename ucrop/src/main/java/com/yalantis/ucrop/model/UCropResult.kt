package com.yalantis.ucrop.model

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.yalantis.ucrop.UCrop

data class UCropResult(
    var mResultCode: Int,
    var mResultData: Intent,
) {

    companion object {

        internal fun getResult(
            uri: Uri?,
            resultAspectRatio: Float,
            offsetX: Int,
            offsetY: Int,
            imageWidth: Int,
            imageHeight: Int,
            extraBundle: Bundle? = null
        ): UCropResult =
            UCropResult(
                Activity.RESULT_OK,
                Intent()
                    .putExtra(UCrop.EXTRA_OUTPUT_URI, uri)
                    .putExtra(UCrop.EXTRA_OUTPUT_CROP_ASPECT_RATIO, resultAspectRatio)
                    .putExtra(UCrop.EXTRA_OUTPUT_IMAGE_WIDTH, imageWidth)
                    .putExtra(UCrop.EXTRA_OUTPUT_IMAGE_HEIGHT, imageHeight)
                    .putExtra(UCrop.EXTRA_OUTPUT_OFFSET_X, offsetX)
                    .putExtra(UCrop.EXTRA_OUTPUT_OFFSET_Y, offsetY).apply {
                        extraBundle?.let {
                            putExtra("EXTRA-BUNDLE", it)
                        }
                    }
            )

        internal fun getError(throwable: Throwable?): UCropResult =
            UCropResult(UCrop.RESULT_ERROR, Intent().putExtra(UCrop.EXTRA_ERROR, throwable))

    }

}