package com.pathus90.springbatchexample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.pathus90.springbatchexample.batch.StudentProcessor;
import com.pathus90.springbatchexample.batch.StudentWriter;
import com.pathus90.springbatchexample.model.Student;
import com.pathus90.springbatchexample.model.StudentFieldSetMapper;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    private static final String FILE_NAME = "results.csv";
    private static final String JOB_NAME = "listStudentsJob";
    private static final String STEP_NAME = "processingStep";
    private static final String READER_NAME = "studentItemReader";

    @Value("${header.names}")
    private String names;

    @Value("${line.delimiter}")
    private String delimiter;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step studentStep() {
        return stepBuilderFactory.get(STEP_NAME)
                .<Student, Student>chunk(5)
                .reader(studentItemReader())
                .processor(studentItemProcessor())
                .writer(studentItemWriter())
                .build();
    }

    @Bean
    public Job listStudentsJob(Step step1) {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step1)
                .build();
    }
    
    @Bean
    public ItemReader<Student> studentItemReader() {
        FlatFileItemReader<Student> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(FILE_NAME));
        reader.setName(READER_NAME);
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;

    }

    @Bean
    public LineMapper<Student> lineMapper() {

        final DefaultLineMapper<Student> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(delimiter);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(names.split(delimiter));

        final StudentFieldSetMapper fieldSetMapper = new StudentFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    @Bean
    public ItemProcessor<Student, Student> studentItemProcessor() {
        return new StudentProcessor();
    }

    @Bean
    public ItemWriter<Student> studentItemWriter() {
        return new StudentWriter();
    }
}
