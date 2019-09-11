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
        toDoAppServices.createTask(task,user);

        //Then
        verify(toDoAppRepo).save(task);
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


//    @Test
//    public void given_test_inputs_when_getParameter_called_then_it_should_verify_related_method() throws Exception {
//
//        //Given
//
//
//        //When
//        toDoAppServices.getParameter("priority",null,null,null,"Asc");
//
//        //Then
//        verify(toDoAppServices).sortBy("priority","Asc");
//    }
    @Test
    public void when_given_parameters_then_call_related_methods_at_least_once() throws Exception {

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

    //
    //
    //
    //

    @Test
    public void given_data_when_type_label_then_call_filterByLabel_method_at_least_once(){
//according to the given dummyTask, label="work", however it is expected that method filter the tasks due to label="moto".
//In this test it doesnot filter by label="moto", it directly takes the dummyTask.
        //Given
        String label = "motorcycle";

        ToDoApp dummyTask = createDummyTask();
        List<ToDoApp> request = Collections.singletonList(dummyTask);
        List<ToDoApp> dummyList = Collections.singletonList(dummyTask);
        when(toDoAppRepo.findByLabel(label)).thenReturn(dummyList);

        //When
        try {
            List<ToDoApp> response = toDoAppServices.filterByLabel(label);
        //Then
            assertThat(response).isEqualTo(request);
            verify(toDoAppRepo).findByLabel(label);
        } catch (Exception ignored){
            fail("not equal");
        }

    }

    @Test
    public void given_data_when_filterByPriority_called_then_call_findByPriority_at_least_once(){

        //Given
        Long priority = 3L;

        //When
        toDoAppServices.filterByPriority(priority);

        //Then
        verify(toDoAppRepo).findByPriority(priority);
    }
    @Test
    public void given_data_when_filterByDueDate_called_then_call_findByDueDate_at_least_once(){

        //Given
        Date dueDate = new Date();

        //When
        toDoAppServices.filterByDueDate(dueDate);

        //Then
        verify(toDoAppRepo).findByDueDate(dueDate);
    }
}
