package ru.dm.myapps.clienvk.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dm.myapps.clienvk.domain.Comment
import ru.dm.myapps.clienvk.utils.genComment

class CommentsViewModel : ViewModel() {

    private var _commentList: MutableLiveData<List<Comment>> =
        MutableLiveData(mutableListOf<Comment>().apply {
            repeat(30) {
                add(genComment(it))
            }
        })

    val commentList: LiveData<List<Comment>>
        get() = _commentList

    private var _isActive: MutableLiveData<Boolean> = MutableLiveData(false)
    val isActive: LiveData<Boolean>
        get() = _isActive

    fun changeStatus() {
        val isActive = _isActive.value ?: false
        _isActive.value = !isActive
    }
}