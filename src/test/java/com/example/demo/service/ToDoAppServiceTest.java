package com.example.demo.service;

import com.example.demo.repository.ToDoAppRepo;
import com.example.demo.model.ToDoApp;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class ToDoAppServiceTest {

    @InjectMocks
    private ToDoAppService toDoAppServices;

    @Mock
    private ToDoAppRepo toDoAppRepo;

    @Mock
    private User user;

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

    private User createDummyUser(){
        User user = new User();
        user.setId(1L);
        user.setName("baran");
        user.setUsername("baranimo");
        user.setEmail("baran@uncosoft.com");
        user.setPassword("bestwordisfuck");
        return user;
    }

    @Test
    public void given_test_data_when_getTasks_called_then_it_should_return_all_tasks(){

        //Given
        ToDoApp task = createDummyTask();
        List<ToDoApp> expectedTasks = Collections.singletonList(task);

        when(toDoAppRepo.findAll()).thenReturn(expectedTasks);

        //When
        List<ToDoApp> actualTasks = toDoAppServices.getTasks();

        //Then
        assertThat(actualTasks).isEqualTo(expectedTasks);
    }

    @Test
    public void given_test_data_when_getTask_called_then_it_should_return_task_matches_with_given_id(){

        //Given
        ToDoApp task = createDummyTask();
        List<ToDoApp> expectedTasks = Collections.singletonList(task);

        when(toDoAppRepo.getOne(task.getId())).thenReturn(task);

        //When
        ToDoApp actualTask = toDoAppServices.getTask(task.getId());

        //Then
        assertThat(actualTask).isEqualTo(task);
    }

    @Test
    public void given_test_data_when_createTask_called_then_it_should_return_that_task() throws Exception {

        //Given
        ToDoApp task = createDummyTask();
        User user = createDummyUser();
        task.setUser(user);

        when(toDoAppRepo.save(task)).thenReturn(task);

        //When
        ToDoApp response = toDoAppServices.createTask(task,user);

        //Then
        assertThat(task).isEqualTo(response);

    }

    @Test
    public void given_test_data_when_deleteTask_called_then_it_should_verify_task_is_deleted() {

        // Given
        final Long taskId = 1L;
        ToDoApp toDoApp = createDummyTask();
        toDoApp.setId(taskId);

        when(toDoAppRepo.getOne(taskId)).thenReturn(toDoApp);

        // When
        toDoAppServices.deleteTask(taskId);

        // Then
        verify(toDoAppRepo).delete(toDoApp);
    }

    @Test
    public void when_given_parameters_then_call_related_methods_at_least_once() throws Exception {

        //this test will be changed

        //When
        toDoAppServices.sortBy("priority","Asc");
        toDoAppServices.sortBy("priority","Desc");
        toDoAppServices.sortBy("task","Asc");
        toDoAppServices.sortBy("dueDate","Asc");
        toDoAppServices.sortBy("dueDate", "Desc");

        //Then
        verify(toDoAppRepo).findByOrderByPriorityAsc();
        verify(toDoAppRepo).findByOrderByPriorityDesc();
        verify(toDoAppRepo).findByOrderByTaskAsc();
        verify(toDoAppRepo).findByOrderByDueDateAsc();
        verify(toDoAppRepo).findByOrderByDueDateDesc();
    }

    @Test
    public void given_data_when_type_label_then_call_filterByLabel_method_at_least_once(){

        //Given
        String label = "motorcycle";

        ToDoApp task1 = createDummyTask();
        task1.setLabel(label);

        List<ToDoApp> toDoAppList = Collections.singletonList(task1);
        when(toDoAppRepo.findByLabel(label)).thenReturn(toDoAppList);

        //When
        List<ToDoApp> response = toDoAppServices.filterByLabel(label);

        //Then
        assertFalse(response.isEmpty());
        assertThat(response.get(0).getLabel()).isEqualTo(label);

    }

    @Test
    public void given_data_when_filterByPriority_called_then_call_findByPriority_at_least_once(){

        //Given
        Long priority = 3L;

        ToDoApp task = createDummyTask();
        task.setPriority(priority);

        List<ToDoApp> list = Collections.singletonList(task);
        when(toDoAppRepo.findByPriority(priority)).thenReturn(list);
        //When
        List<ToDoApp> response = toDoAppServices.filterByPriority(priority);

        //Then
        assertFalse(response.isEmpty());
        assertThat(response.get(0).getPriority()).isEqualTo(priority);

    }
    @Test
    public void given_data_when_filterByDueDate_called_then_call_findByDueDate_at_least_once(){

        //Given
        Date dueDate = new Date();

        ToDoApp task = createDummyTask();
        task.setDueDate(dueDate);

        List<ToDoApp> list = Collections.singletonList(task);
        when(toDoAppRepo.findByDueDate(dueDate)).thenReturn(list);
        //When
        List<ToDoApp> response = toDoAppServices.filterByDueDate(dueDate);

        //Then
        assertFalse(response.isEmpty());
        assertThat(response.get(0).getDueDate()).isEqualTo(dueDate);

    }
}
