package com.minidooray.taskapi.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest

class ProjectRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    void findAllByProjectMemberListMemberSeq() {

    }

    @Test
    void existsByName() {
    }
}