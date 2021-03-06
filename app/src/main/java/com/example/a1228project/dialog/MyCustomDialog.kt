package com.example.a1228project.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.a1228project.R

class MyCustomDialog(context : Context, myInterface: MyCustomDialogInterface) : Dialog(context) {

    // 액티비티에서 인터페이스를 받아옴
    private var myCustomDialogInterface: MyCustomDialogInterface = myInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog)

        var okButton : Button = findViewById(R.id.okButton)
        var cancelButton : Button = findViewById(R.id.cancelButton)
        var memoEditView : EditText = findViewById(R.id.todoEditView)
        var dialogName : TextView = findViewById(R.id.dialogName)

        // 배경 투명하게 바꿔줌
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogName.text = "해야할 일 추가"

        okButton.setOnClickListener {
            val memo = memoEditView.text.toString()

            // 입력하지 않았을 때
            if ( TextUtils.isEmpty(memo)){
                Toast.makeText(context, "해야할 일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

            // 입력 창이 비어 있지 않을 때
            else{
                myCustomDialogInterface.onOkButtonClicked(memo)
                dismiss()
            }
        }

        // 취소 버튼 클릭 시 종료
        cancelButton.setOnClickListener { dismiss()}
    }
}