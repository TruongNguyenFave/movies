package com.madison.gamuda.supervisor.extention.helper.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.text.Html
import android.text.Spanned
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException


const val PHONE_REGEX = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*\$"
const val EXCLUDE_SPECIAL_SYMBOL_REGEX =
    "^[A-Za-z0-9._\\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý]+$"
const val MIN_PASSWORD_LENGTH = 6
const val MAX_PASSWORD_LEGHTH = 16

const val PASSWORD_PROVIDER_ID = "password"
const val FACEBOOK_PROVIDER_ID = "facebook.com"
const val GOOGLE_PROVIDER_ID = "google.com"
const val PHONE_PROVIDER_ID = "phone"

const val PUBLIC_PROFILE_PERMISSION = "public_profile"
const val EMAIL_PERMISSION = "email"

const val BAD_REQUEST_ERROR_CODE = 400
const val UNAUTHORIZED_ERROR_CODE = 401

const val OTP_LENGTH = 6

const val SCROLL_TO_BOTTOM_DELAY_TIME_IN_MILLIS = 200L

const val THUMBNAIL_SIZE = 640

const val DEVICE_TYPE = "android"

// todo update URLs later
const val DYNAMIC_LINK_DOMAIN = "upliftapp.page.link"
const val DYNAMIC_LINK_URL = "https://uplift.com/"

fun convertDpToPx(context: Context, dp: Float): Int {
    val r = context.resources

    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        r.displayMetrics
    ).toInt()
}

fun getScreenWidth(context: Context): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val displayMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

fun getHtmlFormat(htmlText: String): Spanned {
    return if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
        @Suppress("DEPRECATION")
        Html.fromHtml(htmlText)
    } else {
        Html.fromHtml(htmlText)
    }
}

fun scrollToBottomWhenSoftKeyboardAppear(scrollView: NestedScrollView) {
    scrollView.postDelayed({
        val lastChild = scrollView.getChildAt(scrollView.childCount - 1)
        val bottom = lastChild.bottom + scrollView.paddingBottom
        val sy = scrollView.scrollY
        val sh = scrollView.height
        val delta = bottom - (sy + sh)
        scrollView.smoothScrollBy(0, delta)
    }, SCROLL_TO_BOTTOM_DELAY_TIME_IN_MILLIS)
}

fun scrollToBottomWhenSoftKeyboardAppear(scrollView: ScrollView) {
    scrollView.postDelayed({
        val lastChild = scrollView.getChildAt(scrollView.childCount - 1)
        val bottom = lastChild.bottom + scrollView.paddingBottom
        val sy = scrollView.scrollY
        val sh = scrollView.height
        val delta = bottom - (sy + sh)
        scrollView.smoothScrollBy(0, delta)
    }, SCROLL_TO_BOTTOM_DELAY_TIME_IN_MILLIS)
}

@Throws(FileNotFoundException::class, IOException::class)
fun getThumbnail(uri: Uri, context: Context): Bitmap? {
    var input = context.contentResolver.openInputStream(uri)

    val onlyBoundsOptions = BitmapFactory.Options()
    onlyBoundsOptions.inJustDecodeBounds = true
    onlyBoundsOptions.inDither = true//optional
    onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888//optional
    BitmapFactory.decodeStream(input, null, onlyBoundsOptions)
    input?.close()

    if (onlyBoundsOptions.outWidth == -1 || onlyBoundsOptions.outHeight == -1) {
        return null
    }

    val originalSize =
        if (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) onlyBoundsOptions.outHeight else onlyBoundsOptions.outWidth

    val ratio: Double =
        if (originalSize > THUMBNAIL_SIZE) (originalSize / THUMBNAIL_SIZE).toDouble() else 1.0

    val bitmapOptions = BitmapFactory.Options()
    bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio)
    bitmapOptions.inDither = true //optional
    bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888//
    input = context.contentResolver.openInputStream(uri)
    val bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions)
    input?.close()
    return bitmap
}

private fun getPowerOfTwoForSampleRatio(ratio: Double): Int {
    val k = Integer.highestOneBit(Math.floor(ratio).toInt())
    return if (k == 0)
        1
    else
        k
}

fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
    try {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            System.currentTimeMillis().toString(),
            null
        )
        return Uri.parse(path)
    } catch (e: NullPointerException) {
        e.printStackTrace()
    }
    return null
}

fun getFirstLettersFromNameAndEmail(name: String?, email: String?): String {
    if (name.isNullOrEmpty()) {
        if (email.isNullOrEmpty()) {
            return ""
        }
        val emails = email.split(".")
        return if (emails.size > 1) {
            emails[0].first().toString() + emails[1].first().toString()
        } else {
            emails[0].first().toString()
        }

    }
    val names = name.split(" ")

    return if (names.size > 1) {
        names[0].first().toString() + names[1].first().toString()
    } else
        names[0].first().toString()
}