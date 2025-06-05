package com.yalantis.ucrop

import android.app.Dialog
import android.content.Context
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class UCropAspectRatioDialog(
    context: Context,
    private val onAspectRatioSelected: (width: Float, height: Float) -> Unit
) : Dialog(context) {

    private lateinit var widthEditText: EditText
    private lateinit var heightEditText: EditText

    init {
        setupDialog()
    }

    private fun setupDialog() {
        setTitle(context.getString(R.string.ucrop_custom_aspect_ratio_title))

        val mainLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 32, 48, 32)
        }

        // Instruction text
        val instructionText = TextView(context).apply {
            text = context.getString(R.string.ucrop_custom_aspect_ratio_instruction)
            textSize = 14f
            setTextColor(ContextCompat.getColor(context, android.R.color.black))
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 24)
        }
        mainLayout.addView(instructionText)

        // Input container
        val inputContainer = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        // Width input
        widthEditText = createNumberInput().apply {
            hint = context.getString(R.string.ucrop_width_hint)
        }

        // Separator ":"
        val separatorText = TextView(context).apply {
            text = " : "
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, android.R.color.black))
            gravity = Gravity.CENTER
            setPadding(16, 0, 16, 0)
        }

        // Height input
        heightEditText = createNumberInput().apply {
            hint = context.getString(R.string.ucrop_height_hint)
        }

        inputContainer.addView(widthEditText)
        inputContainer.addView(separatorText)
        inputContainer.addView(heightEditText)

        mainLayout.addView(inputContainer)

        // Buttons container
        val buttonContainer = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.END
            setPadding(0, 32, 0, 0)
        }

        // Cancel button
        val cancelButton = Button(context).apply {
            text = context.getString(android.R.string.cancel)
            setOnClickListener { dismiss() }
            background = null
            setTextColor(ContextCompat.getColor(context, R.color.ucrop_color_widget))
        }

        // Apply button
        val applyButton = Button(context).apply {
            text = context.getString(R.string.ucrop_apply)
            setOnClickListener { onApplyClicked() }
            background = null
            setTextColor(ContextCompat.getColor(context, R.color.ucrop_color_active_controls_color))
            setPadding(32, 0, 0, 0)
        }

        buttonContainer.addView(cancelButton)
        buttonContainer.addView(applyButton)

        mainLayout.addView(buttonContainer)

        setContentView(mainLayout)

        // Set dialog window properties
        window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun createNumberInput(): EditText {
        return EditText(context).apply {
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            textSize = 16f
            gravity = Gravity.CENTER
            setPadding(16, 16, 16, 16)
            setTextColor(ContextCompat.getColor(context, android.R.color.black))
        }
    }

    private fun onApplyClicked() {
        val widthText = widthEditText.text.toString().trim()
        val heightText = heightEditText.text.toString().trim()

        if (widthText.isEmpty() || heightText.isEmpty()) {
            showError(context.getString(R.string.ucrop_error_empty_fields))
            return
        }

        try {
            val width = widthText.toFloat()
            val height = heightText.toFloat()

            if (width <= 0 || height <= 0) {
                showError(context.getString(R.string.ucrop_error_invalid_values))
                return
            }

            onAspectRatioSelected(width, height)
            dismiss()

        } catch (e: NumberFormatException) {
            showError(context.getString(R.string.ucrop_error_invalid_number))
        }
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun setCurrentValues(width: Float, height: Float) {
        if (width > 0 && height > 0) {
            widthEditText.setText(width.toInt().toString())
            heightEditText.setText(height.toInt().toString())
        }
    }
}