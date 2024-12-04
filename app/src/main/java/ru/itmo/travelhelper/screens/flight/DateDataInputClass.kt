package ru.itmo.travelhelper.screens.flight

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText


class DateInputMask(private val editText: EditText) : TextWatcher {

    private var isDeleting = false
    private val maxLength = 10
    private var lastLength = 0
    private var isEditing = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        lastLength = s?.length ?: 0
        if (count > 0) {
            isDeleting = true
        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (!isDeleting) {
            if (editText.text.length in listOf(2,5) && editText.text[editText.text.length-1] != '.') {
                editText.text.append('.')
            }
        }

    }

    override fun afterTextChanged(s: Editable?) {
        if (isDeleting) {
            isDeleting = false
        }

        if (isEditing) return

        isEditing = true

        if ((s?.length ?: 0) > maxLength) {
            s?.delete(maxLength, s.length)
        }

        if (s?.length!! > 0 && ((s?.length) != 3 && (s?.length) != 6) && s!![s.length-1] == '.') {
            s.delete(s.length-1,s.length)

        }
        else if (lastLength > s?.length ?: 0) {
            val currentIndex = s?.length ?: 0
            if (currentIndex > 0 && s?.get(currentIndex - 1) == '.') {
                s?.delete(currentIndex - 1, currentIndex)
            }
        }
        else if ((s?.length) == 3 && (s[2] != '.')) {
            s.insert(2, ".")
        }

        else if ((s?.length) == 6 && s[5] != '.') {
            s.insert(5, ".")
        }
        else if ((s?.length == 4) && (!listOf('0', '1','.').contains(s[3]))) {
            s?.insert(3, "0")
        }
        else if ((s?.length == 1) && (!listOf('0', '1','2','3','.').contains(s[0]))) {
            s?.insert(0, "0")
        }
        isEditing = false

    }
}


