package com.Kalana.HireHub.exception;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(Long jobId) { super("Job with id " + jobId + " not found"); }

}
