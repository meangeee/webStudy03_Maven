package kr.or.ddit.springbatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BatchExecutionQuartzJob {
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job personJob;

	@Test
	public void batchJobExecute() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// 이름만 다를 뿐 build method임
		JobParameters parameters = new JobParametersBuilder()
										.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(personJob, parameters);
		ExitStatus status = jobExecution.getExitStatus();
		System.err.println(status);
	}
}
