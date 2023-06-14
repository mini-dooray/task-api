package com.minidooray.taskapi.tag.service;

import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.project.exception.NotFoundProjectException;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.tag.dto.request.RequestTagDto;
import com.minidooray.taskapi.tag.dto.response.ResponseTagDto;
import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.tag.exception.NotFoundTagException;
import com.minidooray.taskapi.tag.repository.TagRepository;
import com.minidooray.taskapi.tag.service.impl.TagServiceImpl;
import com.minidooray.taskapi.tasktag.repository.TaskTagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {
    @Mock
    private TagRepository tagRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private TaskTagRepository taskTagRepository;
    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    void createTag() {
        //given
        Long projectSeq = 1L;
        RequestTagDto dto = new RequestTagDto();
        ReflectionTestUtils.setField(dto, "name", "name");

        //when
        when(projectRepository.findById(projectSeq))
                .thenReturn(Optional.of(new Project()));

        tagService.createTag(projectSeq, dto);

        //then
        verify(tagRepository).save(any());
    }

    @Test
    @DisplayName("createTag : NotFoundTagException 테스트")
    void createTagFail() {
        //given
        Long projectSeq = 1L;
        RequestTagDto dto = new RequestTagDto();
        ReflectionTestUtils.setField(dto, "name", "name");

        //when
        when(projectRepository.findById(projectSeq))
                .thenReturn(Optional.empty());

        try {
            tagService.createTag(projectSeq, dto);
        } catch (NotFoundProjectException e) {
            //then
            assertThat(e.getClass()).isEqualTo(NotFoundProjectException.class);
        }
    }

    @Test
    void updateTag() {
        //given
        Long tagSeq = 1L;
        RequestTagDto dto = new RequestTagDto();
        ReflectionTestUtils.setField(dto, "name", "name");

        //when
        when(tagRepository.findById(tagSeq))
                .thenReturn(Optional.of(new Tag()));
        tagService.updateTag(tagSeq, dto);

        //then
        verify(tagRepository).findById(any());
    }

    @Test
    @DisplayName("updateTag : NotFoundTagException 테스트")
    void updateTagFail() {
        //given
        Long tagSeq = 1L;
        RequestTagDto dto = new RequestTagDto();
        ReflectionTestUtils.setField(dto, "name", "name");

        //when
        when(tagRepository.findById(tagSeq))
                .thenReturn(Optional.empty());

        try {
            //then
            tagService.updateTag(tagSeq, dto);
        } catch (NotFoundTagException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundTagException.class);
        }
    }

    @Test
    void getTag() {
        //given
        Long tagSeq = 1L;

        //when
        ResponseTagDto tag = tagService.getTag(tagSeq);

        //then
        verify(tagRepository).findBySeq(tagSeq);
        assertThat(tag).isNull();
    }

    @Test
    void getTags() {
        //given
        Long projectSeq = 1L;

        //when
        tagService.getTags(projectSeq);

        //then
        verify(tagRepository).findAllByProjectSeq(projectSeq);

    }

    @Test
    void deleteTag() {
        //given
        Long tagSeq = 1L;

        //when
        tagService.deleteTag(tagSeq);

        //then
        verify(taskTagRepository).deleteByTagSeq(tagSeq);
        verify(tagRepository).deleteById(tagSeq);
    }

    @Test
    void checkDuplicateName() {
        //given
        String name = "name";

        //when
        tagService.checkDuplicateName(name);

        //then
        verify(tagRepository).existsByName(name);
    }
}