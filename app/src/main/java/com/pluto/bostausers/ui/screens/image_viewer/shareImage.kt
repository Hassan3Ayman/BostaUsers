package com.pluto.bostausers.ui.screens.image_viewer

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import java.io.File
import java.io.FileOutputStream

fun Context.shareImage(title: String, image: Drawable, filename: String) {
    val outputDir = File(cacheDir, "images")
    if (!outputDir.exists()) {
        outputDir.mkdirs()
    }

    val file = try {
        val outputFile = File(outputDir, "$filename.png")
        val outPutStream = FileOutputStream(outputFile)
        image.toBitmap().compress(Bitmap.CompressFormat.PNG, 100, outPutStream)
        outPutStream.flush()
        outPutStream.close()
        outputFile
    } catch (e: Exception) {
        return toast(e)
    }

    val uri = file.toUriCompat(this)
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "image/png"
        putExtra(Intent.EXTRA_STREAM, uri)
    }
    startActivity(Intent.createChooser(shareIntent, title))
}

fun File.toUriCompat(context: Context): Uri {
    return FileProvider.getUriForFile(context, "com.pluto.bostausers.provider", this)
}

fun Context.toast(throwable: Exception) =
    throwable.message?.let { toast(it) }
        ?: toast("Un known")
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}