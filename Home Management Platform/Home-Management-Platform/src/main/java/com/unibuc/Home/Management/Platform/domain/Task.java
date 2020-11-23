package com.unibuc.Home.Management.Platform.domain;
import lombok.*;

import java.util.Date;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String name;
    private String description;
    private String priority;
    private Date startDate;
    private Date endDate;
}
