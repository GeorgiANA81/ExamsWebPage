package com.exams.backend.spec;

import com.exams.backend.entity.ExamEntity;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "academicYear", spec = Equal.class),
        @Spec(path = "session", spec = Like.class),
        @Spec(path = "yearOfStudy", spec = Equal.class),
        @Spec(path = "faculty", spec = Like.class),
        @Spec(path = "section", spec = Like.class),
        @Spec(path = "course", spec = Like.class),
        @Spec(path = "teacher", spec = Like.class),
        @Spec(path = "numberOfSeats", spec = Equal.class),
        @Spec(path = "date", spec = Equal.class),
})
public interface ExamSpec extends Specification<ExamEntity> {
}
