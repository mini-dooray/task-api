package com.minidooray.taskapi.comment.service;


import com.minidooray.taskapi.comment.dto.request.RequestCommentDto;
import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.comment.entity.CommentPeriod;
import com.minidooray.taskapi.comment.exception.NotFoundCommentException;
import com.minidooray.taskapi.comment.repository.CommentRepository;
import com.minidooray.taskapi.comment.service.impl.CommentServiceImpl;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.task.entity.Task;
import com.minidooray.taskapi.task.exception.NotFoundTaskException;
import com.minidooray.taskapi.task.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        commentService = Mockito.mock(CommentServiceImpl.class);
    }

    @Test
    void testCreateComment() {
        //given
        CommentPeriod period = mock(CommentPeriod.class);
        Member member = mock(Member.class);
        Task task = mock(Task.class);
        Comment comment = new Comment(1L, "test",
                new CommentPeriod(LocalDate.now(), LocalDate.now())
                , new Task()
                , new Member());
        RequestCommentDto dto = new RequestCommentDto("test", 1L, 1L, LocalDate.now(), LocalDate.now());

        //when
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));
        when(commentRepository.save(any(Comment.class))).thenReturn(mock(Comment.class));

        commentRepository.save(comment);
        commentService.createComment(dto);
        memberRepository.findById(1L);
        taskRepository.findById(dto.getTaskSeq()).orElseThrow(NotFoundTaskException::new);
        period.uploadLastUpdateDate(dto.getLastUpdateDate());

        //then
        verify(commentRepository).save(comment);
        verify(memberRepository).findById(anyLong());
        verify(taskRepository).findById(anyLong());
        verify(commentService, times(1)).createComment(dto);
        verify(period).uploadLastUpdateDate(any(LocalDate.class));
    }

    @Test
    void testGetComment() {
        //given
        Comment comment = new Comment(1L, "test",
                new CommentPeriod(LocalDate.now(), LocalDate.now())
                , new Task()
                , new Member());

        //when
        commentService.getComment(comment.getSeq());
        //then
        verify(commentService).getComment(1L);
    }

    @Test
    void testGetComments() {
        //givne
        Comment comment = new Comment(1L, "test",
                new CommentPeriod(LocalDate.now(), LocalDate.now())
                , new Task()
                , new Member());
        //when
        commentService.getComments(comment.getSeq());
        //then
        verify(commentService).getComments(1L);
    }

    @Test
    void testUpdateComment_Success() {
        //given
        Comment comment = new Comment();
        RequestCommentDto dto = new RequestCommentDto();

        //when
        when(commentRepository.findBySeq(1L)).thenReturn(comment);

        commentRepository.findBySeq(1L);
        commentService.updateComment(dto, 1L);
        //then
        verify(commentRepository, times(1)).findBySeq(1L);
        verify(commentService).updateComment(dto, 1L);
    }

    @Test
    void testUpdateComment_Failure() {
        //given
        RequestCommentDto dto = new RequestCommentDto();
        //when
        when(commentRepository.findBySeq(1L)).thenReturn(null);

        commentRepository.findBySeq(1L);
        commentService.updateComment(dto, 1L);
        try {
            //then
            verify(commentRepository, times(1)).findBySeq(1L);
            verify(commentService).updateComment(dto, 1L);
        } catch (NotFoundCommentException e) {
            Assertions.assertThat(e.getClass()).isEqualTo(NotFoundCommentException.class);
        }
    }


}
