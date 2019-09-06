package com.example.demo.controller;


import com.example.demo.Controller.ToDoAppController;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.ToDoAppService;
import com.example.demo.model.ToDoApp;
import com.example.demo.model.User;
import com.example.demo.security.UserPrincipal;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class ToDoAppControllerTest {

    @InjectMocks
    private ToDoAppController toDoAppController;

    @Mock
    private ToDoAppService toDoAppServices;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private UserRepository userRepository;

    private ToDoApp createDummyTask() {
        ToDoApp task = new ToDoApp();
        task.setTask("hello");
        task.setLabel("work");
        task.setComplete(false);
        task.setDueDate(new Date());
        task.setPriority(2L);
        task.setId(1L);
        return task;
    }

    @Test
    public void given_test_data_when_get_specific_task_called_then_it_should_return_that_task(){

        //Given
        final Long taskId = 2L;
        ToDoApp dummyTask = createDummyTask();
        ToDoApp request = createDummyTask();
        dummyTask.setId(taskId);

        when(toDoAppServices.getTask(taskId)).thenReturn(request);
        //When
        ToDoApp response = toDoAppController.getSpecificTask(taskId);
        //Then
        assertThat(response).isNotEqualTo(dummyTask);

        verify(toDoAppServices).getTask(taskId);
    }

    @Test
    public void given_test_data_when_create_new_task_called_then_it_should_create_task() {

        // Given
        User user = new User();
        user.setId(1L);
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        ToDoApp request = createDummyTask();
        ToDoApp task = createDummyTask();

        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
        when(userRepository.getOne(1L)).thenReturn(user);
        try {
            when(toDoAppServices.createTask(request, user)).thenReturn(task);
        } catch (Exception ignored) {}

        try {

            // When
            ToDoApp response = toDoAppController.createNewTask(request);

            // Then
            assertEquals(response.getId(), request.getId());

        } catch (Exception e) {
            fail("raised exception");
        }
    }

    @Test
    public void return_edited_task_that_given_id(){

        //Given
        ToDoApp dummyTask = createDummyTask();
        final Long id = dummyTask.getId();

        when(toDoAppServices.saveOrUpdateTask(dummyTask,id)).thenReturn(dummyTask);
        //When
        toDoAppController.editTask(dummyTask,id);
        //Then
        verify(toDoAppServices).saveOrUpdateTask(dummyTask,id);
    }

    @Test
    public void given_id_when_delete_selected_task_called_then_return_string(){

        //Given
        final Long id = 1L;
        //When
        toDoAppController.deleteSelectedTask(id);
        //Then
        verify(toDoAppServices).deleteTask(id);
    }

    @Test
    public void given_parameters_when_get_all_or_sort_or_filter_called_then_return_task_list(){


    }








}
