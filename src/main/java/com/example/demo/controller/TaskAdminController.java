package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.dto.TaskDtos;
import com.example.demo.service.CamundaProcessService;
import com.example.demo.service.LoanService;
import com.example.demo.service.TasklistService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class TaskAdminController {
    private final TasklistService tasklistService;
    private final LoanService loanService;
    private final CamundaProcessService processService;

    public TaskAdminController(TasklistService tasklistService, LoanService loanService, CamundaProcessService processService) {
        this.tasklistService = tasklistService;
        this.loanService = loanService;
        this.processService = processService;
    }

    @GetMapping("/tasks")
    public Object tasks(@RequestParam(defaultValue = "") String processKey,
                        @RequestParam(defaultValue = "") String assignee,
                        @RequestParam(defaultValue = "CREATED") String state) {
        return tasklistService.searchTasks(processKey, assignee, state);
    }

    @PostMapping("/tasks/{taskId}/claim")
    public void claim(@PathVariable String taskId, @RequestBody TaskDtos.TaskActionRequest request) { tasklistService.claim(taskId, request.assignee()); }
    @PostMapping("/tasks/{taskId}/unassign")
    public void unassign(@PathVariable String taskId) { tasklistService.unassign(taskId); }
    @PostMapping("/tasks/{taskId}/complete")
    public void complete(@PathVariable String taskId, @RequestBody LoanDtos.TaskCompleteRequest request) { tasklistService.complete(taskId, request.variables()); }

    @GetMapping("/loans/overdue")
    public Object overdue() { return loanService.overdueLoans(); }
    @PostMapping("/processes/deploy")
    public void deploy(){ processService.deployResources(); }
    @GetMapping("/processes/{requestId}")
    public Object byRequest(@PathVariable String requestId){ return processService.getInstanceStatusByBusinessId(requestId); }
}
