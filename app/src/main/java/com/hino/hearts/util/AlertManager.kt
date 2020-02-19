package com.hino.hearts.util

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.hino.hearts.R
import com.hino.hearts.adapter.VisitTargetDialogAdapter
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * Created by Dihardja Software on 2020-02-14.
 */
class AlertManager {

    companion object {
        private var instance: AlertManager? = null

        fun getInstance(): AlertManager {
            if (instance == null) {
                instance = AlertManager()
            }
            return instance!!
        }
    }

    fun showVisitTargetDialog(
        context: Context?,
        date: String?,
        clickListener: DialogInterface.OnClickListener,
        adapter: VisitTargetDialogAdapter
        ) {
        val dialog: Dialog = AppCompatDialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.layout_custom_dialog_visit)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val closeBtn = dialog.findViewById<FrameLayout>(R.id.fl_close)
        when (closeBtn != null){
            true -> {
                closeBtn.onClick {
                    dialog.dismiss()
                }
            }
        }

        val tvDialogDate = dialog.findViewById<TextView>(R.id.tv_dialog_date)
        when (tvDialogDate != null) {
            true -> tvDialogDate.text = date
        }

        val rvDialog = dialog.findViewById<RecyclerView>(R.id.rv_visit_target)
        when(rvDialog != null){
            true -> {
                rvDialog.layoutManager = LinearLayoutManager(context)
                rvDialog.adapter = adapter
            }
        }

        val btnDialog =
            dialog.findViewById<MaterialButton>(R.id.btn_record_activity_detail)
        when (btnDialog != null) {
            true -> {
                btnDialog.onClick {
                    clickListener.onClick(dialog, 0)
                }
            }
        }

        dialog.show()
    }
}