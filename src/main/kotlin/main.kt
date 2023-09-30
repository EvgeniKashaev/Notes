fun main() {

}

data class Notes(
    val id: Int = 0,
    val title: String = "Название",
    var text: String = "текст заметки"
)   /* Заметки */

data class Comment(
    var statusComment: Boolean = true,
    var idNote: Int,
    var idComment: Int,
    var text: String = "null"
) /* Комментарии */

object NoteService {

    private val noteList = mutableListOf<Notes>()
    private val commentList = mutableListOf<Comment>()

    fun add(note: Notes): Notes {
        noteList += note.copy()
        return noteList.last()
    }                               /* Создает новую заметку у текущего пользователfun */

    fun createComment(comment: Comment) {
        commentList += comment.copy()

    }              /* Добавляет новый комментарий к заметкee */

    fun delete(id: Int) {
        for (i: Notes in noteList) {
            if (id == i.id) {
                noteList.remove(i)
                break
            }
        }
    }          /* Удаляет заметку текущего пользовател */

    fun deleteComment(noteId: Int, commentId: Int) {
        for (i in commentList) {
            if (i.idNote == noteId && i.idComment == commentId) {
                i.statusComment = false
            }
        }
    }              /* Удаляет комментарий к заметке */

    fun edit(id: Int, text: String): Notes {
        for (i: Notes in noteList) {
            if (id == i.id) {
                i.text = text
                break
            }
        }
        return noteList.last()
    }                              /* Редактирует заметку текущего пользовател */

    fun editComment(idNote: Int, idComment: Int, text: String): Comment {
        for (i in commentList) {
            if (i.idNote == idNote && i.idComment == idComment) i.text = text
        }
        return commentList.last()
    }                 /* Редактирует указанный комментарий у заметк */

    fun get(): MutableList<Notes> {
        return noteList
    }                                                   /* Возвращает список заметок, созданных пользователе */


    fun getById(note: Notes): Any {

        val index: Int = noteList.indexOf(note)
        if (index != -1) {
            return noteList[index]
        }
        return println("Заметка не найдена")
    }                                                   /* Возвращает заметку по её id */


    fun getComments(noteId: Int): MutableList<Comment> {
        val commentToNote: MutableList<Comment> = mutableListOf()
        for (i: Comment in commentList) {
            println("0")
            if (i.idNote == noteId && i.statusComment) {
                commentToNote += i
            }
        }
        return commentToNote
    } /* Возвращает список комментариев к заметке */

    fun restoreComment(commentId: Int, noteId: Int) {
        for (i in commentList) {
            if (i.idNote == noteId && i.idComment == commentId) {
                i.statusComment = true
            }
        }
    }
    /* Восстанавливает удалённый комментарий. */
}
