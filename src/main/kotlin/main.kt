fun main() {}

data class Notes(
    val text: String, val id: Int, val comment: MutableList<Comment> = mutableListOf()
)

data class Comment(
    val text: String, val id: Int, val status: Boolean = false
)

class NoteService<T>(val id: T, val text: T, private val notesList: MutableCollection<Notes> = mutableListOf()) {

    private fun add(note: Notes): Notes {
        notesList.add(note)

        return notesList.last()
    }                                            //    Создает новую заметку у текущего пользователfun

    private fun createComment(comment: Comment, note: Notes): Boolean {
        return note.comment.add(comment)
    }              //    Добавляет новый комментарий к заметкee

    private fun delete(id: Int): Boolean {
        val notes = notesList.find { it.id == id }
        return notesList.remove(notes)

    }                                           //    Удаляет заметку текущего пользовател

    private fun deleteComment(id: Int, note: Notes, comment: Comment): Boolean {
        if (!comment.status) {
            notesList.find { it.id == id }
            note.comment.remove(comment)
            return note.comment.remove(comment)
        }
        return false// TODO Проверит!
    }     //    Удаляет комментарий к заметке

    private fun edit(id: Int, note: Notes) {
        delete(id)
        add(note)
    }                                         //    Редактирует заметку текущего пользовател

    private fun editComment(id: Int, note: Notes, comment: Comment) {
        if (comment.status) {
            if (id == comment.id) {
                deleteComment(id, note, comment)
                createComment(comment, note)
            } else println("Не выполненно")
        }
    }                //    Редактирует указанный комментарий у заметк

    private fun get(id: Int): MutableCollection<Notes> {
        val notes = notesList
        return notes
    }                             //    Возвращает список заметок, созданных пользователе

    private fun getById(id: Int): Notes? {
        return notesList.find { it.id == id }
    }                                           //    Возвращает заметку по её id

    private fun getComments(id: Int): MutableList<Comment> {
        return getById(id)!!.comment
    }                         //    Возвращает список комментариев к заметке
}


