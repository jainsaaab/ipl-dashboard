package com.jainsaab.ipldashboard.model;

import java.util.List;

import com.jainsaab.ipldashboard.exception.IplDashboardException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SendErrorEmailRequest {
    List<String> to;
    String serviceName;
    IplDashboardException exception;
}