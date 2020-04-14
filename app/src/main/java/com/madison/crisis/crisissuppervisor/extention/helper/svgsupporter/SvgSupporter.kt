package com.madison.crisis.crisissuppervisor.extention.helper.svgsupporter

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

class SvgSupporter constructor(context: Context) {
    var requestBuilder: RequestBuilder<PictureDrawable> =
        GlideApp.with(context).`as`(PictureDrawable::class.java).transition(withCrossFade())
            .listener(SvgSoftwareLayerSetter())

    fun loadSvg(imageView: ImageView, url: String?) {
        val uri = Uri.parse(url)
        requestBuilder.load(uri).into(imageView)
    }
}