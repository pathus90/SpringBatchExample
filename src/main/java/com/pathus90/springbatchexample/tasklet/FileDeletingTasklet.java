package com.pathus90.springbatchexample.tasklet;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class FileDeletingTasklet implements Tasklet {

    private String directory;
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final int DAYS_BEFORE_PURGE = 62;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        File[] files = new File(directory).listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                LocalDate creationDate = getDateFromFolderName(file.getName());
                if (ChronoUnit.DAYS.between(creationDate, LocalDate.now()) >= DAYS_BEFORE_PURGE) {
                    FileUtils.deleteDirectory(file);
                }
            }
        }
        return RepeatStatus.FINISHED;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    private LocalDate getDateFromFolderName(String folderName) {
        return LocalDate.parse(folderName, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

}
