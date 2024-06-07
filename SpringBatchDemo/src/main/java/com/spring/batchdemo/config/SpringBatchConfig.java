package com.spring.batchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.spring.batchdemo.entity.Employee;
import com.spring.batchdemo.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

//	private  JobBuilderFactory jobBuilderFactory;
//	
//	private  StepBuilderFactory stepBuilderFactory;
	
	private EmployeeRepository empRepo;
	private  JobRepository jobRepository;
    private  PlatformTransactionManager transactionManager;

   
    public SpringBatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager, EmployeeRepository empRepo) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.empRepo = empRepo;
    }
	
	@Bean
	public FlatFileItemReader<Employee> reader(){
		FlatFileItemReader<Employee> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource("/src/main/resources/employeedata.csv"));
		itemReader.setName("CSVREADER");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}

	private LineMapper<Employee> lineMapper() {
		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames("id","fullName","jobTitle","department","businessUnit","gender","ethnicity","age","hireDate","annualSalary","bonus","country","city","exitDate");
		BeanWrapperFieldSetMapper<Employee> fieldMapper = new BeanWrapperFieldSetMapper<>();
		fieldMapper.setTargetType(Employee.class);
		lineMapper.setFieldSetMapper(fieldMapper);
		lineMapper.setLineTokenizer(delimitedLineTokenizer);
		return lineMapper;
	}
	
	@Bean
	public EmployeeProcessor processor() {
		return new EmployeeProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<Employee> writer(){
		RepositoryItemWriter<Employee> writer = new RepositoryItemWriter<>();
		writer.setRepository(empRepo);
		writer.setMethodName("save");
		return writer;
	}
//
//    @Bean
//    Step step1() {
//		
//		return stepBuilderFactory.get("csv-step").<Employee,Employee>chunk(10)
//				.reader(reader())
//				.processor(processor())
//				.writer(writer())
//				.build();
//	}
    
    @Bean
    public Step step1() {
        return new StepBuilder("csv-step", jobRepository)
                .<Employee, Employee>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
	
//	@SuppressWarnings("deprecation")
//	@Bean
//	public Job runJob() {
//		return jobBuilderFactory.get("importEmployee").flow(step1()).end().build();
//	}
	
    @Bean
    public Job runJob() {
        return new JobBuilder("importEmployee", jobRepository)
                .start(step1())
                .build();
    }
	
}
