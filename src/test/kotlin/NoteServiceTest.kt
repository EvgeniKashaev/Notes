import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test


class NoteServiceTest {

    @Before
    fun clear() {
        NoteService.clear()
    }

    @Test
    fun add() {
        val note = Notes(1, "1", "1")
        val result = NoteService.add(note)
        assertEquals(result, note)
    }

    @Test
    fun createComment() {
        val comment = Comment(true, 1, 1, text = "1")
        val result = NoteService.createComment(comment)
        assertEquals(result.text, comment.text)

    }

    @Test
    fun deleteTrue() {
        val notes = Notes(1, "1", "1")
        val idNote = 1
        NoteService.add(notes)
        val result = NoteService.delete(idNote)
        assertTrue(result)

    }

    @Test
    fun deleteFalse() {
        val notes = Notes(1, "1", "1")
        val idNote = 2
        NoteService.add(notes)
        val result = NoteService.delete(idNote)
        assertFalse(result)
    }

    @Test
    fun deleteCommentFalse() {
        val comment = Comment(true, 1, 1, text = "1")
        NoteService.createComment(comment)
        NoteService.deleteComment(1, 1)
        val list = NoteService.commentList
        val result = list.last().statusComment
        assertFalse(result)
    }

    @Test
    fun deleteCommentTrue() {
        val comment = Comment(true, 1, 1, text = "1")
        NoteService.createComment(comment)
        NoteService.deleteComment(2, 1)
        val list = NoteService.commentList
        val result = list.last().statusComment
        assertTrue(result)
    }

    @Test
    fun deleteCommentTrue1() {
        val comment = Comment(true, 1, 1, text = "1")
        NoteService.createComment(comment)
        NoteService.deleteComment(1, 2)
        val list = NoteService.commentList
        val result = list.last().statusComment
        assertTrue(result)
    }

    @Test
    fun edit() {
        val text = "редактирована"
        val notes = Notes(1, "1", "1")
        NoteService.add(notes)
        val result = NoteService.edit(1, text)
        assertEquals(result, text)
    }

    @Test
    fun edit2() {
        val text = "редактирована"
        val notes = Notes(1, "1", "1")
        NoteService.add(notes)
        val result = NoteService.edit(2, text)
        assertEquals(result, "1")
    }

    @Test
    fun editComment() {
        val text = "редактирована"
        val comment = Comment(true, 1, 1, text = "1")
        NoteService.createComment(comment)
        val result = NoteService.editComment(1, 1, text)
        assertEquals(result, text)
    }

    @Test
    fun editComment1() {
        val text = "редактирована"
        val comment = Comment(true, 1, 1, text = "1")
        NoteService.createComment(comment)
        val result = NoteService.editComment(2, 1, text)
        assertEquals(result, "1")
    }

    @Test
    fun editComment2() {
        val text = "редактирована"
        val comment = Comment(true, 1, 1, text = "1")
        NoteService.createComment(comment)
        val result = NoteService.editComment(1, 2, text)
        assertEquals(result, "1")
    }

    @Test
    fun get() {
        val note = Notes(1, "1", "1")
        val note1 = Notes(2, "2", "2")
        NoteService.add(note)
        NoteService.add(note1)
        val result = NoteService.get()
        assertEquals(result, mutableListOf(note, note1))
    }

    @Test
    fun getByIdOk() {
        val note = Notes(1, "1", "1")
        NoteService.add(note)
        val result = NoteService.getById(1)
        assertEquals(result, note)
    }

    @Test
    fun getByIdFalse() {
        val note = Notes(1, "1", "1")
        NoteService.add(note)
        val result = NoteService.getById(2)
        assertFalse(result as Boolean)
    }

    @Test
    fun getComments() {
        val comment = Comment(true, 1, 1, text = "1")
        val comment1 = Comment(true, 1, 2, text = "2")
        NoteService.createComment(comment)
        NoteService.createComment(comment1)
        val result = NoteService.getComments(1)
        assertEquals(result, mutableListOf(comment, comment1))
    }

    @Test
    fun getComments1() {
        val comment = Comment(true, 1, 1, text = "1")
        val comment1 = Comment(false, 1, 2, text = "2")
        NoteService.createComment(comment)
        NoteService.createComment(comment1)
        val result = NoteService.getComments(1)
        assertEquals(result, mutableListOf(comment))
    }

    @Test
    fun getComments2() {
        val comment = Comment(true, 1, 1, text = "1")
        val comment1 = Comment(true, 2, 2, text = "2")
        NoteService.createComment(comment)
        NoteService.createComment(comment1)
        val result = NoteService.getComments(1)
        assertEquals(result, mutableListOf(comment))
    }

    @Test
    fun restoreComment() {
        val comment = Comment(false, 1, 1, text = "1")
        NoteService.createComment(comment)
        val result = NoteService.restoreComment(1, 1)
        assertTrue(result)
    }

    @Test
    fun restoreComment1() {
        val comment = Comment(false, 1, 1, text = "1")
        NoteService.createComment(comment)
        val result = NoteService.restoreComment(2, 1)
        assertFalse(result)
    }

    @Test
    fun restoreComment2() {
        val comment = Comment(false, 1, 1, text = "1")
        NoteService.createComment(comment)
        val result = NoteService.restoreComment(1, 2)
        assertFalse(result)
    }
}