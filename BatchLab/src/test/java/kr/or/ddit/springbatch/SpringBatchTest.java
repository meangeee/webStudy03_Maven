package kr.or.ddit.springbatch;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobInstanceException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//JUNIT필요
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/kr/or/ddit/springbatch/*-context.xml")
public class SpringBatchTest {
   
//   @inject가 없으므로 Autowiredfh 객체 주입
   @Autowired 
   JobLauncher jobLauncher;

   @Autowired
   JobExplorer jobExplorer;
   
   @Autowired
   JobRegistry jobRegistry;
   
   @Autowired
   JobOperator jobOperator;

   @Test
   public void jobLaunchTest() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, NoSuchJobException, JobInstanceAlreadyExistsException, NoSuchJobInstanceException{
//      toJobParameters() = bulid()메서드 
//      Job personJob = jobRegistry.getJob("personJob");
//      JobParameters parameters = new JobParametersBuilder()
//                              .toJobParameters();
//      JobExecution jobExecution = jobLauncher.run(personJob, parameters);
      long executionId = jobOperator.start("personJob", "");
      
      JobExecution jobExecution = jobExplorer.getJobExecution(executionId);
      ExitStatus status = jobExecution.getExitStatus(); //상태정보 
      System.err.println(status);
      List<String> names = jobExplorer.getJobNames();
      System.out.println(names);
   }
   
   

}