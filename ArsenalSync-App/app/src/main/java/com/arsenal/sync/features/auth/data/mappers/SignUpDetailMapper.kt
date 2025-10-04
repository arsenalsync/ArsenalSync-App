package com.arsenal.sync.features.auth.data.mappers

import com.arsenal.sync.features.auth.data.dto.SignUpDetailDto
import com.arsenal.sync.features.auth.domain.model.SignUpDetail

fun SignUpDetail.toSignUpDetailDto(id: String): SignUpDetailDto {
    return SignUpDetailDto(
        id = id,
        firstName = this.firstName.trim(),
        email = this.email.trim()
    )
}