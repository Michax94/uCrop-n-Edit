package com.yalantis.ucrop.model

import android.os.Parcelable
import com.yalantis.ucrop.view.CropImageView
import com.yalantis.ucrop.view.OverlayView
import kotlinx.parcelize.Parcelize

/**
 * Created by Oleksii Shliama [https://github.com/shliama] on 6/24/16.
 * Modified to support freestyle mode per aspect ratio.
 */

@Parcelize
sealed class AspectRatio : Parcelable {
    abstract val aspectRatioTitle: String?
    abstract val aspectRatioX: Float
    abstract val aspectRatioY: Float
    @OverlayView.FreestyleMode abstract val freestyleMode: Int

    @Parcelize
    data class Preset(
        override val aspectRatioTitle: String?,
        override val aspectRatioX: Float,
        override val aspectRatioY: Float,
        @OverlayView.FreestyleMode override val freestyleMode: Int
    ) : AspectRatio() {

        constructor(
            aspectRatioTitle: String?,
            aspectRatioX: Float,
            aspectRatioY: Float
        ) : this(aspectRatioTitle, aspectRatioX, aspectRatioY, OverlayView.FREESTYLE_CROP_MODE_DISABLE)

    }

    @Parcelize
    data class Custom(
        override val aspectRatioTitle: String,
        override val aspectRatioX: Float,
        override val aspectRatioY: Float,
        @OverlayView.FreestyleMode override val freestyleMode: Int
    ) : AspectRatio() {

        constructor(
            aspectRatioTitle: String
        ) : this(
            aspectRatioTitle = aspectRatioTitle,
            aspectRatioX = CropImageView.SOURCE_IMAGE_ASPECT_RATIO,
            aspectRatioY = CropImageView.SOURCE_IMAGE_ASPECT_RATIO,
            freestyleMode = OverlayView.FREESTYLE_CROP_MODE_ENABLE_WITH_ASPECT_RATIO)

    }
}