package com.yalantis.ucrop.model

import android.os.Parcel
import android.os.Parcelable
import com.yalantis.ucrop.view.OverlayView

/**
 * Created by Oleksii Shliama [https://github.com/shliama] on 6/24/16.
 * Modified to support freestyle mode per aspect ratio.
 */
class AspectRatio : Parcelable {
    val aspectRatioTitle: String?
    val aspectRatioX: Float
    val aspectRatioY: Float
    @OverlayView.FreestyleMode val freestyleMode: Int

    constructor(
        aspectRatioTitle: String?,
        aspectRatioX: Float,
        aspectRatioY: Float,
        @OverlayView.FreestyleMode freestyleMode: Int = OverlayView.FREESTYLE_CROP_MODE_DISABLE
    ) {
        this.aspectRatioTitle = aspectRatioTitle
        this.aspectRatioX = aspectRatioX
        this.aspectRatioY = aspectRatioY
        this.freestyleMode = freestyleMode
    }

    constructor(
        aspectRatioTitle: String?,
        aspectRatioX: Float,
        aspectRatioY: Float
    ) : this(aspectRatioTitle, aspectRatioX, aspectRatioY, OverlayView.FREESTYLE_CROP_MODE_DISABLE)

    constructor(parcel: Parcel) {
        aspectRatioTitle = parcel.readString()
        aspectRatioX = parcel.readFloat()
        aspectRatioY = parcel.readFloat()
        freestyleMode = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(aspectRatioTitle)
        dest.writeFloat(aspectRatioX)
        dest.writeFloat(aspectRatioY)
        dest.writeInt(freestyleMode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AspectRatio> {
        override fun createFromParcel(parcel: Parcel): AspectRatio {
            return AspectRatio(parcel)
        }

        override fun newArray(size: Int): Array<AspectRatio?> {
            return arrayOfNulls(size)
        }
    }
}