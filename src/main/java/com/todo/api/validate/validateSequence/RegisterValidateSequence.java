package com.todo.api.validate.validateSequence;

import com.todo.api.dtos.requestDto.RegisterReq;
import com.todo.api.validate.itf.NotBlankGroup;
import com.todo.api.validate.itf.SizeGroup;
import jakarta.validation.GroupSequence;

@GroupSequence({NotBlankGroup.class, SizeGroup.class, RegisterReq.class})
public interface RegisterValidateSequence {
}
