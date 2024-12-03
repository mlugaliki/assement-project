package com.kcb.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SummaryDto {
    private String projectName;
    private List<TaskList> taskSummary;
}
