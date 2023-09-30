fun main() {
    val comment = Comment(false, 1, 1, text = "1")

    NoteService.createComment(comment)
    NoteService.restoreComment(1, 1)

    val list = NoteService.commentList
    val result = list.last().statusComment
    println(NoteService.commentList)

}

data class Notes(
    val id: Int = 0, val title: String = "Названи", var text: String = "текст заметки"
)   /* Заметки */

data class Comment(
    var statusComment: Boolean = true, var idNote: Int, var idComment: Int, var text: String = "null"
) /* Комментарии */

object NoteService {


    val noteList = mutableListOf<Notes>()
    val commentList = mutableListOf<Comment>()


    fun add(note: Notes): Notes {
        noteList += note.copy()
        return noteList.last()
    }                               /* Создает новую заметку у текущего пользователfun */

    fun createComment(comment: Comment): Comment {
        commentList += comment.copy()
        return commentList.last()

    }              /* Добавляет новый комментарий к заметкee */

    fun delete(id: Int): Boolean {
        for (i: Notes in noteList) {
            if (id == i.id) {
                noteList.remove(i)
                return true
            }
        }
        return false
    }          /* Удаляет заметку текущего пользовател */

    fun deleteComment(noteId: Int, commentId: Int) {
        for (i in commentList) {
            if (i.idNote == noteId && i.idComment == commentId) {
                i.statusComment = false
            }
        }
    }              /* Удаляет комментарий к заметке */

    fun edit(id: Int, text: String): String {
        for (i: Notes in noteList) {
            if (id == i.id) {
                i.text = text
            }
        }
        return noteList.last().text
    }                              /* Редактирует заметку текущего пользовател */

    fun editComment(idNote: Int, idComment: Int, text: String): String {
        for (i in commentList) {
            if (i.idNote == idNote && i.idComment == idComment) {
                i.text = text
            }
        }
        return commentList.last().text
    }                 /* Редактирует указанный комментарий у заметк */

    fun get(): MutableList<Notes> {
        return noteList
    }                                                   /* Возвращает список заметок, созданных пользователе */


    fun getById(id: Int): Any {
        for (i: Notes in noteList) {
            if (id == i.id) {
                return i
            }
        }
        return false
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

    fun restoreComment(commentId: Int, noteId: Int): Boolean {
        for (i in commentList) {
            if (i.idNote == noteId && i.idComment == commentId) {
                i.statusComment = true
                return i.statusComment
            }
        }
        return false
    }/* Восстанавливает удалённый комментарий. */

    fun clear() {
        noteList.clear()
        commentList.clear()
    }
}
