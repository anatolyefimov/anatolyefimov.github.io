package com.nodepipes.core.model.messaging.wrapper

import com.nodepipes.core.model.messaging.message.Message

interface NodeOutput {

    fun getMessage(): Message

    fun toInput(): NodeInput
}