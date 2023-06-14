package com.minidooray.taskapi.comment.service.impl;

import com.minidooray.taskapi.comment.dto.request.RequestCommentDto;
import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.comment.entity.CommentPeriod;
import com.minidooray.taskapi.comment.repository.CommentRepository;
import com.minidooray.taskapi.comment.service.CommentService;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.membertask.repository.MemberTaskRepository;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepository;
import com.minidooray.taskapi.task.entity.Task;
import com.minidooray.taskapi.task.exception.NotFoundTaskException;
import com.minidooray.taskapi.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;


    @Transactional(readOnly = true)
    public Comment getComment(Long commentSeq) {
        return commentRepository.findBySeq(commentSeq);
    }


    @Transactional(readOnly = true)
    public List<Comment> getComments(Long memberSeq) {
        return commentRepository.findAllBySeq(memberSeq);
    }

    @Transactional
    public void createComment(RequestCommentDto dto) {
        CommentPeriod period = new CommentPeriod();
        Member member = memberRepository.findById(dto.getMemberSeq()).orElseThrow(NotFoundMemberException::new);
        Task task = taskRepository.findById(dto.getTaskSeq()).orElseThrow(NotFoundTaskException::new);

//        period.setLastUpdateDate(LocalDate.now());
//
//        period.setRegisteredDate(dto.getRegisteredDate());

        period.uploadRegisterDate(dto);

        period.uploadLastUpdateDate(LocalDate.now());


        Comment comment = Comment.builder()
                .content(dto.getContent())
                .period(period)
                .member(member)
                .task(task)
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(RequestCommentDto dto, Long commentSeq) {
        Comment comment = commentRepository.findBySeq(commentSeq);
        CommentPeriod period = comment.getPeriod();
        period.uploadLastUpdateDate(LocalDate.now());

        comment.modifyCommentContent(dto);
    }
    @Transactional
    public void deleteComment(Long taskSeq, Long memberSeq, Long commentSeq) {
        Comment comment = commentRepository.findBySeq(commentSeq);
        if (comment != null && comment.getTask().getSeq().equals(taskSeq) && comment.getMember().getSeq().equals(memberSeq)) {
            commentRepository.delete(comment);
        }
    }

}
