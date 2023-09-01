fun main() {
}

data class Notes(

    val title: String, val text: String, val id: Int

) // Заметки

data class Comment(
    var statusComment: Boolean = true, var idNote: Int, var idComment: Int, val text: String
) // Комментарии

class NoteService() {
    private val noteList = mutableListOf<Notes>()
    private val commentList = mutableListOf<Comment>()

    private fun add(note: Notes): Notes {
        noteList += note.copy()
        return noteList.last()
    }                               //    Создает новую заметку у текущего пользователfun

    private fun createComment(comment: Comment, id: Int) {
        for (i in noteList.indices) {
            if (noteList[id].id == comment.idNote) {
                commentList += comment.copy()
            }
        }
    }              //    Добавляет новый комментарий к заметкee

    private fun delete(note: Notes, comment: Comment) {
        if (comment.statusComment) {
            commentList.removeAt(comment.idComment)
        } // При удаление заметки должен удаляется комментарий т.к заметка не является востонавливаеым объектом
    }          //    Удаляет заметку текущего пользовател

    fun deleteComment(comment: Comment, noteId: Int, commentId: Int) {
        for (i in commentList) {
            if (i.idNote == noteId && i.idComment == commentId) {
                comment.statusComment = false
            }
        }
    }              //    Удаляет комментарий к заметке

    private fun edit(note: Notes): Notes {
        noteList[note.id] = note
        return noteList.last()
    }                              //    Редактирует заметку текущего пользовател

    private fun editComment(comment: Comment): Comment {
        commentList[comment.idComment] = comment
        return commentList.last()
    }                 //    Редактирует указанный комментарий у заметк

    private fun get(): MutableList<Notes> {
        return noteList
    }                                                   //    Возвращает список заметок, созданных пользователе

    private fun getById(note: Notes, id: Int): Notes {
        return noteList[id]
    }                                                   //    Возвращает заметку по её id

    private fun getComments(comment: Comment, noteId: Int, commentId: Int): MutableList<Comment> {
        val commentToNote = mutableListOf<Comment>()
        for (i in commentList) {
            if (i.idNote == noteId && i.idComment == commentId && comment.statusComment) {
                    commentToNote += comment.copy()
            }
        }
        return commentToNote
    }                         //    Возвращает список комментариев к заметке
}