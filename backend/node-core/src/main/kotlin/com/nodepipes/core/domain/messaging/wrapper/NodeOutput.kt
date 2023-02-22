package com.nodepipes.core.domain.messaging.wrapper

import com.nodepipes.core.domain.messaging.message.Message

interface NodeOutput {

    fun getMessage(): Message

    fun toInput(): NodeInput
}