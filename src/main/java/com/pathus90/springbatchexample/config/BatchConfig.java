package com.pathus90.springbatchexample.config;

import com.pathus90.springbatchexample.tasklet.FileDeletingTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableBatchProcessing
@EnableScheduling
@RequiredArgsConstructor
public class BatchConfig {

    private static final String JOB_NAME = "purgeOutDatedFilesJob";

    @Value("${piv.parameters.server.path}")
    private String serverPathAbsolute;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step FileDeletingStep() {
        return stepBuilderFactory.get("fileDeletingStep").tasklet(fileDeletingTasklet()).build();
    }

    @Bean
    public FileDeletingTasklet fileDeletingTasklet() {
        FileDeletingTasklet tasklet = new FileDeletingTasklet();
        tasklet.setDirectory(serverPathAbsolute);
        return tasklet;
    }

    @Bean
    public Job FileDeletingJob(Step step) {
        return jobBuilderFactory.get(JOB_NAME)
            .start(step)
            .build();
    }

}
