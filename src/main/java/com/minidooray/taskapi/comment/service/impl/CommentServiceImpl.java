package com.minidooray.taskapi.comment.service.impl;

import com.minidooray.taskapi.comment.dto.request.RequestCommentDto;
import com.minidooray.taskapi.comment.dto.response.ResponseCommentDto;
import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.comment.repository.CommentRepository;
import com.minidooray.taskapi.comment.service.CommentService;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.membertask.repository.MemberTaskRepository;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepository;
import com.minidooray.taskapi.task.entity.Task;
import com.minidooray.taskapi.task.repository.TaskRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class CommentServiceImpl extends QuerydslRepositorySupport implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final MemberTaskRepository memberTaskRepository;
    private final EntityManager entityManager;

    private final ProjectMemberRepository projectMemberRepository;
    private final TaskRepository taskRepository;

    public CommentServiceImpl(CommentRepository commentRepository, MemberRepository memberRepository, ProjectRepository projectRepository, MemberTaskRepository memberTaskRepository, ProjectMemberRepository projectMemberRepository, TaskRepository taskRepository, EntityManager entityManager) {
        super(Comment.class);
        this.memberTaskRepository = memberTaskRepository;
        this.entityManager = entityManager;
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.taskRepository = taskRepository;
    }

    /*
    SELECT NEW com.minidooray.taskapi.comment.dto.response.ResponseCommentDto(
    comment.content,
    comment.period.registeredDate,
    comment.period.lastUpdateDate
    )
        FROM Comment comment
        JOIN comment.task task
        JOIN task.project project
        WHERE project.seq = :projectSeq
        AND task.seq = :taskSeq
        AND comment.member.seq = :memberSeq
     */

    @Transactional(readOnly = true)
    public ResponseCommentDto getComment(Long projectSeq, Long taskSeq, Long memberSeq, Long commentSeq) {
//        JPAQuery<ResponseCommentDto> query = new JPAQuery<>(entityManager);
//
//        return query.select(
//                        Projections.constructor(
//                                ResponseCommentDto.class,
//                                comment.content,
//                                comment.period.registeredDate,
//                                comment.period.lastUpdateDate
//                        ))
//                .from(comment)
//                .join(comment.task, task)
//                .join(task.project, project)
//                .where(project.seq.eq(projectSeq)
//                        .and(task.seq.eq(taskSeq))
//                        .and(comment.member.seq.eq(memberSeq)))
//                .fetchOne();
        return null;
    }


    /*
    INSERT INTO Comment (content, registeredDate, lastUpdateDate, task_seq, writer_seq)
    SELECT :content, :registeredDate, :lastUpdateDate, task.seq, member.seq
    FROM Task task, Project project, Member member
    WHERE task.seq = :taskSeq
    AND project.seq = :projectSeq
    AND member.seq = :memberSeq
     */
    @Transactional
    public void createComment(RequestCommentDto dto, Long projectSeq, Long taskSeq, Long memberSeq) {
//        authorizedCheck(memberSeq, projectSeq);
//        authorizedCheck2(memberSeq, taskSeq);
//
//        QComment qComment = QComment.comment;
//        QTask qTask = QTask.task;
//        QProject qProject = QProject.project;
//        QMember qMember = QMember.member;
//
//        Comment comment = new Comment();
//        comment.setContent(dto.getContent());
//        CommentPeriod period = new CommentPeriod();
//        period.setRegisteredDate(dto.getRegisteredDate());
//        period.setLastUpdateDate(dto.getLastUpdateDate());
//
//        JPAQuery<Comment> query = new JPAQuery<>(entityManager);
//
//        Task task = query.select(qTask)
//                .from(qTask)
//                .where(qTask.seq.eq(taskSeq))
//                .fetchOne();
//
//        Project project = query.select(qProject)
//                .from(qProject)
//                .where(qProject.seq.eq(projectSeq))
//                .fetchOne();
//
//        Member member = query.select(qMember)
//                .from(qMember)
//                .where(qMember.seq.eq(memberSeq))
//                .fetchOne();
//        task.setProject(project);
//        comment.setPeriod(period);
//        comment.setTask(task);
//        comment.setMember(member);
//
//        entityManager.persist(comment);
    }

    /*
    UPDATE Comment
    SET content = :content, lastUpdateDate = :lastUpdateDate
    WHERE seq = :commentSeq
        AND EXISTS (
        SELECT 1
        FROM Task task
        JOIN task.project project
        JOIN Comment comment ON comment.task = task
        JOIN comment.member member
        WHERE comment.seq = :commentSeq
            AND project.seq = :projectSeq
            AND task.seq = :taskSeq
            AND member.seq = :memberSeq
    )
     */
    @Transactional
    public void updateComment(RequestCommentDto dto, Long projectSeq, Long taskSeq, Long memberSeq, Long commentSeq) {
//        authorizedCheck(memberSeq, projectSeq);
//        authorizedCheck2(memberSeq, taskSeq);
//
//        QComment qComment = QComment.comment;
//        QTask qTask = QTask.task;
//        QProject qProject = QProject.project;
//        QMember qMember = QMember.member;
//
//        JPAQuery<Comment> query = new JPAQuery<>(entityManager);
//
//        Comment comment = query.select(qComment)
//                .from(qComment)
//                .join(qComment.task, qTask)
//                .join(qTask.project, qProject)
//                .join(qComment.member, qMember)
//                .where(qComment.seq.eq(commentSeq)
//                        .and(qProject.seq.eq(projectSeq))
//                        .and(qTask.seq.eq(taskSeq))
//                        .and(qMember.seq.eq(memberSeq)))
//                .fetchOne();
//
//        comment.setContent(dto.getContent());
//        CommentPeriod period = new CommentPeriod();
//        period.setLastUpdateDate(dto.getLastUpdateDate());
//        comment.setPeriod(period);
//
//        entityManager.merge(comment);

    }

    /*
    DELETE comment
    FROM Comment comment
    JOIN comment.task task
    JOIN task.project project
    JOIN comment.member member
    WHERE comment.seq = :commentSeq
    AND project.seq = :projectSeq
    AND task.seq = :taskSeq
    AND member.seq = :memberSeq
     */
    @Transactional
    public void deleteComment(Long projectSeq, Long taskSeq, Long memberSeq, Long commentSeq) {
//        QComment qComment = QComment.comment;
//        QTask qTask = QTask.task;
//        QProject qProject = QProject.project;
//        QMember qMember = QMember.member;
//
//        JPAQuery<Comment> query = new JPAQuery<>(entityManager);
//
//        Comment comment = query.select(qComment)
//                .from(qComment)
//                .join(qComment.task, qTask)
//                .join(qTask.project, qProject)
//                .join(qComment.member, qMember)
//                .where(qComment.seq.eq(commentSeq)
//                        .and(qProject.seq.eq(projectSeq))
//                        .and(qTask.seq.eq(taskSeq))
//                        .and(qMember.seq.eq(memberSeq)))
//                .fetchOne();
//        entityManager.remove(comment);

    }

    private void authorizedCheck(Long memberSeq, Long projectSeq) {
//        if (!projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq, projectSeq)) {
//            throw new UnauthorizedException();
//        }
    }

    public void authorizedCheck2(Long memberSeq, Long taskSeq) {
//        if (!memberTaskRepository.existsByMemberSeqAndTaskSeq(memberSeq, taskSeq)) {
//            throw new UnauthorizedException();
//        }
    }


    private void setComment(Comment comment, RequestCommentDto dto, Task task, Long memberSeq) {

//        task.setTitle(dto.getTitle());
//        task.setContent(dto.getContent());
//        task.setUploadFile(dto.getUploadFile());
//        TaskPeriod taskPeriod = new TaskPeriod();
//        taskPeriod.setRegisteredDate(LocalDate.now());
//        task.setTaskPeriod(taskPeriod);
//        task.setProject(project);
//        Milestone milestone = milestoneRepository.findById(dto.getMilestoneSeq())
//                .orElseThrow(NotFoundMilestoneException::new);
//        task.setMilestone(milestone);
//        Priority priority = priorityRepository.findById(dto.getPrioritySeq())
//                .orElseThrow(NotFoundProjectException::new);
//        task.setPriority(priority);
//        Member member = memberRepository.findById(memberSeq)
//                .orElseThrow(NotFoundMemberException::new);
//        task.setRegistrant(member);
    }
}
