package com.aio.kotlin.utils.dialog

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View

object DialogUtils {

    /**
     * Show AlertDialog with a single button.
     *
     * @param context The context to use.
     * @param title The title of the dialog.
     * @param message The message of the dialog.
     * @param buttonText The text for the single button.
     * @param onClickListener The listener for the button click.
     */
    fun showSingleButtonDialog(
        context: Context,
        title: String?,
        message: String?,
        buttonText: String,
        onClickListener: View.OnClickListener?
    ) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            singleCustomDialog(context, title, message, buttonText, onClickListener)
        } else {
            Handler(Looper.getMainLooper()).post {
                singleCustomDialog(context, title, message, buttonText, onClickListener)
            }
        }
    }

    private fun singleCustomDialog(
        context: Context,
        title: String?,
        message: String?,
        buttonText: String,
        onClickListener: View.OnClickListener?
    ) {
        val dialog = CustomDialog(context).apply {
            this.title = title
            this.msg = message
            this.mPBtText = buttonText
            this.mPListener = onClickListener
        }
        dialog.show()
    }

    /**
     * Show AlertDialog with two buttons.
     *
     * @param context The context to use.
     * @param title The title of the dialog.
     * @param message The message of the dialog.
     * @param positiveButtonText The text for the positive button.
     * @param negativeButtonText The text for the negative button.
     * @param onPositiveClickListener The listener for the positive button click.
     * @param onNegativeClickListener The listener for the negative button click.
     * @param isVertical Whether the buttons should be arranged vertically.
     */
    fun showTwoButtonDialog(
        context: Context,
        title: String?,
        message: String?,
        positiveButtonText: String,
        negativeButtonText: String,
        onPositiveClickListener: View.OnClickListener?,
        onNegativeClickListener: View.OnClickListener?,
    ) {
        val dialog = CustomDialog(context).apply {
            this.title = title
            this.msg = message
            this.mPBtText = positiveButtonText
            this.mNBtText = negativeButtonText
            this.mPListener = onPositiveClickListener
            this.mNListener = onNegativeClickListener
        }
        dialog.show()
    }
}
